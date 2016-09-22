package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowCash implements Action {
    public static final String JSP_PAGE_NAME_CASH = "cash";
    public static final String ATTRIBUTE_NAME_USER_ID = "userId";
    public static final String ATTRIBUTE_NAME_USER = "user";
    UserService userService;

    public ShowCash() throws ActionException {
        userService = new UserService();
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute(ATTRIBUTE_NAME_USER_ID);
        User user;
        try {
            user = userService.findById(userId);
        } catch (ServiceException e) {
            throw new ActionException("can't get user by id from service", e);
        }
        req.setAttribute(ATTRIBUTE_NAME_USER, user);
        return new ActionResult(JSP_PAGE_NAME_CASH);
    }
}
