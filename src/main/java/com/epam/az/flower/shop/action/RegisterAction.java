package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction extends AddUser {
    public static final String JSP_PAGE_NAME_PROFILE = "profile";
    public static final String JSP_PAGE_NAME_REGISTRATION = "registration";
    private UserService userService;
    private static Logger logger = LoggerFactory.getLogger(RegisterAction.class);
    public RegisterAction() throws ActionException {
        try {
            userService = new UserService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) throws ActionException {

        boolean isValidate = validate(request);
        if (isValidate == false) {

            return new ActionResult(JSP_PAGE_NAME_REGISTRATION);
        }
        try {
            User user = fillUser(request, new User());
            user = userService.registerUser(user);
            putInSession(user, request);
            return new ActionResult(JSP_PAGE_NAME_PROFILE, true);
        } catch (ServiceException e) {
            throw new ActionException("can't register new user", e);
        }
    }


}

