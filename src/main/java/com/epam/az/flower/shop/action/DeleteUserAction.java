package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteUserAction implements Action{
    UserService userService = new UserService();
    LogoutAction logoutAction = new LogoutAction();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        userService.delete(userId);
        logoutAction.execute(req, resp);

        return new ActionResult("login", true);
    }
}
