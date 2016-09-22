package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction extends AddUser {
    public static final String JSP_PAGE_NAME_PROFILE = "profile";

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) throws ActionException {
        ActionResult actionResult = validate(request);
        if (actionResult != null) {
            return actionResult;
        }
        registerUser(request, new User());
        return new ActionResult(JSP_PAGE_NAME_PROFILE, true);

    }
}

