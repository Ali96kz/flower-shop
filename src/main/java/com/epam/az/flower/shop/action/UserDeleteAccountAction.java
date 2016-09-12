package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserDeleteAccountAction implements Action {
    private UserService userService = new UserService();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        userService.delete(userId);
        return new ActionResult("delete-profile");
    }
}
