package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction extends AddUser {
    private UserService userService;

    public RegisterAction() throws ActionException {
        try {
            userService = new UserService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) throws ActionException {
        ActionResult actionResult = validate(request);
        if (actionResult != null) {
            return actionResult;
        }
        try {
            User user = fillUser(request, new User());
            setUserRole(user, request);
            user = userService.registerUser(user);
            putInSession(user, request);
            actionResult = new ActionResult("profile", true);
            return actionResult;
        } catch (ServiceException e) {
            throw new ActionException("", e);
        }

    }
}

