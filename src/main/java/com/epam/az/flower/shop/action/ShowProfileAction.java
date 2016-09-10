package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowProfileAction implements Action {
    UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        ActionResult actionResult = new ActionResult("profile");
        HttpSession session= req.getSession();

        int i = (int) session.getAttribute("userId");
        User user = null;
        try {
            user = userService.findById(i);
        } catch (ServiceException e) {
            throw new ActionException("can't get user from service", e);
        }
        req.setAttribute("user", user);

        return actionResult;
    }
}
