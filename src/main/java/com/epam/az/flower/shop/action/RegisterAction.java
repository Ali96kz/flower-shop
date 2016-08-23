package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction implements Action {
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        userService.registUser(new User());
        return null;
    }
}
