package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction extends AddUser{
    private UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) {
        ActionResult actionResult = validate(request);
        if(actionResult != null){
            return actionResult;
        }

        User user = fillUser(request);
        setUserRole(user, request);
        user = userService.registerUser(user);
        putInSession(user, request);

        actionResult = new ActionResult("profile", true);
        return actionResult;
    }
}

