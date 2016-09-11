package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteUserAction implements Action {
    UserService userService = new UserService();
    StringAdapter stringAdapter = new StringAdapter();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();
        userService.logout((Integer) session.getAttribute("userId"));

        session.invalidate();
        int userId = stringAdapter.toInt(req.getParameter("id"));
        userService.delete(userId);

        return new ActionResult("delete-profile", true);

    }
}
