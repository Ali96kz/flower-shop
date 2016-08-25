package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowLoginAction implements Action{
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            if (session.getAttribute("userId") != null) {
                ActionResult actionResult = new ActionResult("profile", true);
                return actionResult;
            }
        }
        ActionResult actionResult = new ActionResult("login");
        return actionResult;
    }

}

