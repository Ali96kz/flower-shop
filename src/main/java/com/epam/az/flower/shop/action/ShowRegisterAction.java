package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowRegisterAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (session != null || session.getAttribute("userId") != null) {
            return new ActionResult("profile", true);
        }
        ActionResult actionResult = new ActionResult("registration");
        return actionResult;
    }
}
