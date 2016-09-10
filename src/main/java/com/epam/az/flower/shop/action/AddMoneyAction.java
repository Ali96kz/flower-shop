package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.validator.BalanceValidator;
import com.epam.az.flower.shop.validator.Validator;
import com.epam.az.flower.shop.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddMoneyAction implements Action {
    UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        Validator validator = new BalanceValidator();
        List<String> errorMsg = null;
        try {
            errorMsg = validator.isValidate(req);
        } catch (ValidatorException e) {
            throw new ActionException("problem with validator balance ", e);
        }

        if (errorMsg.size() > 0) {
            req.setAttribute("errorMsg", errorMsg);
            return new ActionResult("cash", true);
        }

        int userId = (int) session.getAttribute("userId");
        int money  = Integer.parseInt(req.getParameter("money"));

        User user = null;
        try {
            user = userService.findById(userId);
        } catch (ServiceException e) {
            throw new ActionException("can;t get user from service", e);
        }
        userService.addMoneyToBalance(user, money);
        return new ActionResult("cash", true);
    }
}