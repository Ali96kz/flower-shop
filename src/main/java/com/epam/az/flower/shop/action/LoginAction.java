package com.epam.az.flower.shop.action;


import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.validator.LogInValidator;
import com.epam.az.flower.shop.validator.Validator;
import com.epam.az.flower.shop.validator.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginAction implements Action {
    public static final String JSP_PAGE_NAME_PROFILE = "profile";
    public static final String PARAMETER_NICK_NAME = "nickName";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String ATTRIBUTE_NAME_USER_ID = "userId";
    public static final String JSP_PAGE_NAME_LOGIN = "login";
    public static final String ATTRIBUTE_NAME_ERROR_MSG = "errorMsg";

    private static Logger log = LoggerFactory.getLogger(LoginAction.class);
    private Validator validator = new LogInValidator();
    private UserService userService;
    public LoginAction() throws ActionException {
        try {
            userService = new UserService();

        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();

        List<String> errorMsg;
        try {
            errorMsg = validator.isValidate(req);
        } catch (ValidatorException e) {
            throw new ActionException("problem with validating ", e);
        }
        if (errorMsg.size() > 0) {
            req.setAttribute(ATTRIBUTE_NAME_ERROR_MSG, errorMsg);
            return new ActionResult(JSP_PAGE_NAME_LOGIN);
        }

        String nickName = req.getParameter(PARAMETER_NICK_NAME);
        String password = req.getParameter(PARAMETER_PASSWORD);

        int userId;
        try {
            userId = userService.getUserByCredentials(nickName, password);
        } catch (ServiceException e) {
            throw new ActionException("can't find user by id", e);
        }

        session.setAttribute(ATTRIBUTE_NAME_USER_ID, userId);
        return new ActionResult(JSP_PAGE_NAME_PROFILE, true);
        }
}
