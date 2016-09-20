package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowCash implements Action {
    public static final String JSP_PAGE_NAME_CASH = "cash";
    UserService userService;

    public ShowCash() throws ActionException {
        try {
            userService = new UserService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize class", e);
        }
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        User user = null;
        try {
            user = userService.findById(userId);
        } catch (ServiceException e) {
            throw new ActionException("can't get user by id from service", e);
        }
        req.setAttribute("user", user);
        return new ActionResult(JSP_PAGE_NAME_CASH);
    }
}
