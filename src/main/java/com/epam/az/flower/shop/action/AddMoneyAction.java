package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.validator.AddMoneyValidator;
import com.epam.az.flower.shop.validator.Validator;
import com.epam.az.flower.shop.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddMoneyAction implements Action {
    private static final String MENU_ERROR_MSG = "errorMsg";
    private static final String JSP_PAGE_NAME_CASH = "cash";
    private static final String PARAMETER_NAME_MONEY = "money";
    private static final String ATTRIBUTE_NAME_USER = "user";
    private static final String SESSION_PARAMETER_NAME_USER_ID = "userId";
    private UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            HttpSession session = req.getSession();
            Validator validator = new AddMoneyValidator();
            List<String> errorMsg = validator.isValidate(req);
            int userId = (int) session.getAttribute(SESSION_PARAMETER_NAME_USER_ID);

            User user = userService.findById(userId);

            if (errorMsg.size() > 0) {
                req.setAttribute(MENU_ERROR_MSG, errorMsg);
                req.setAttribute(ATTRIBUTE_NAME_USER, user);
                return new ActionResult(JSP_PAGE_NAME_CASH);
            }

            int money = Integer.parseInt(req.getParameter(PARAMETER_NAME_MONEY));
            userService.addMoneyToBalance(user, money);
            return new ActionResult(JSP_PAGE_NAME_CASH, true);
        } catch (ServiceException e) {
            throw new ActionException("can't get user from service", e);
        } catch (ValidatorException e) {
            throw new ActionException("can't validate", e);
        }
    }
}