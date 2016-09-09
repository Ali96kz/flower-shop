package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowCash implements Action {
    UserService userService = new UserService();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        User user = userService.findByID(userId);
        req.setAttribute("user", user);
        return new ActionResult("cash");
    }
}
