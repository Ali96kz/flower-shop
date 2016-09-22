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
    public static final String MENU_ERROR_MSG = "errorMsg";
    public static final String JSP_PAGE_NAME_CASH = "cash";
    private UserService userService = new UserService();
    public static final String PARAMETER_NAME_MONEY = "money";
    public static final String ATTRIBUTE_NAME_USER = "user";
    public static final String SESSION_PARAMETER_NAME_USER_ID = "userId";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            HttpSession session = req.getSession();
            Validator validator = new BalanceValidator();
            List<String> errorMsg;
            int userId = (int) session.getAttribute(SESSION_PARAMETER_NAME_USER_ID);
            User user = userService.findById(userId);

            try {
                errorMsg = validator.isValidate(req);
            } catch (ValidatorException e) {
                throw new ActionException("problem with validator balance ", e);
            }

            if (errorMsg.size() > 0) {
                req.setAttribute(MENU_ERROR_MSG, errorMsg);
                req.setAttribute(ATTRIBUTE_NAME_USER, user);
                return new ActionResult(JSP_PAGE_NAME_CASH);
            }

            int money = Integer.parseInt(req.getParameter(PARAMETER_NAME_MONEY));
            user = userService.findById(userId);
            userService.addMoneyToBalance(user, money);

            return new ActionResult(JSP_PAGE_NAME_CASH, true);
        } catch (ServiceException e) {
            throw new ActionException("can;t get user from service", e);
        } catch (ValidatorException e) {
            throw new ActionException("can't validate object", e);
        }
    }
}