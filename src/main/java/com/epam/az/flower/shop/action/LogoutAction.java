package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action{
    UserService userService = new UserService();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {


        HttpSession session = req.getSession();
        userService.logout((Integer) session.getAttribute("userId"));
        session.invalidate();
        return new ActionResult("registration", true);
    }
}
