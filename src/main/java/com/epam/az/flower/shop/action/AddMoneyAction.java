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
    UserService userService;

    public AddMoneyAction() throws ActionException {
        try {
            userService = new UserService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }


    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            HttpSession session = req.getSession();
            Validator validator = new BalanceValidator();
            List<String> errorMsg;
            int userId = (int) session.getAttribute("userId");
            User user = userService.findById(userId);
            try {
                errorMsg = validator.isValidate(req);
            } catch (ValidatorException e) {
                throw new ActionException("problem with validator balance ", e);
            }

            if (errorMsg.size() > 0) {
                req.setAttribute("errorMsg", errorMsg);
                req.setAttribute("user", user);
                return new ActionResult("cash");
            }

            int money = Integer.parseInt(req.getParameter("money"));
            user = userService.findById(userId);
            userService.addMoneyToBalance(user, money);
            return new ActionResult("cash", true);

        } catch (ServiceException e) {
            throw new ActionException("can;t get user from service", e);
        } catch (ValidatorException e) {
            throw new ActionException("can't validate object", e);
        }
    }
}