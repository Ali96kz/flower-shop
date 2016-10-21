package com.epam.az.flower.shop.action;


import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.util.Hasher;
import com.epam.az.flower.shop.validator.LogInValidator;
import com.epam.az.flower.shop.validator.Validator;
import com.epam.az.flower.shop.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginAction implements Action {
    private static final String JSP_PAGE_NAME_PROFILE = "profile";
    private static final String PARAMETER_NICK_NAME = "nickName";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String ATTRIBUTE_NAME_USER_ID = "userId";
    private static final String JSP_PAGE_NAME_LOGIN = "login";
    private static final String ATTRIBUTE_NAME_ERROR_MSG = "errorMsg";
    private Validator validator = new LogInValidator();
    private UserService userService = new UserService();
    private Hasher hasher = new Hasher();


    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            HttpSession session = req.getSession();

            List<String> errorMsg = validator.isValidate(req);

            if (errorMsg.size() > 0) {
                req.setAttribute(ATTRIBUTE_NAME_ERROR_MSG, errorMsg);
                return new ActionResult(JSP_PAGE_NAME_LOGIN);
            }

            String nickName = req.getParameter(PARAMETER_NICK_NAME);
            String password = req.getParameter(PARAMETER_PASSWORD);

            int userId;
            userId = userService.getUserIdByCredentials(nickName, hasher.hash(password));

            session.setAttribute(ATTRIBUTE_NAME_USER_ID, userId);
            return new ActionResult(JSP_PAGE_NAME_PROFILE, true);
        } catch (ValidatorException e) {
            throw new ActionException("problem with validating ", e);
        } catch (ServiceException e) {
            throw new ActionException("can't get user from data", e);
        }


    }
}
