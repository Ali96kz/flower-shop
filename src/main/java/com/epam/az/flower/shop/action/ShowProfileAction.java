package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowProfileAction implements Action {
    UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        ActionResult actionResult = new ActionResult("profile");
        HttpSession session= req.getSession();

        if (session == null) {
            return new ActionResult("login", true);
        }

        if (session != null) {
            if (session.getAttribute("userId") == null) {
                return new ActionResult("login", true);
            }
        }

        int i = (int) session.getAttribute("userId");
        User user = userService.getUserByID(i);
        req.setAttribute("user", user);

        return actionResult;
    }
}
