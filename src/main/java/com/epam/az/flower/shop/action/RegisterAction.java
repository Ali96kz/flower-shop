package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction extends AddUser {
    private static final String JSP_PAGE_NAME_PROFILE = "profile";
    private static final String JSP_PAGE_NAME_REGISTRATION = "registration";
    private UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) throws ActionException {
        try {
            boolean isValidate = validate(request);
            if (isValidate == false) {

                return new ActionResult(JSP_PAGE_NAME_REGISTRATION);
            }

            User user = fillUser(request, new User());
            user = userService.registerUser(user);
            putInSession(user, request);
            return new ActionResult(JSP_PAGE_NAME_PROFILE, true);
        } catch (ServiceException e) {
            throw new ActionException("can't register new user", e);
        }
    }


}

