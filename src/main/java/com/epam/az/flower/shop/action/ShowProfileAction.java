package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowProfileAction implements Action {
    public static final String JSP_PAGE_NAME_PROFILE = "profile";
    private UserService userService;
    public static final String SESSION_PARAMETER_USER_ID = "userId";
    public static final String ATTRIBUTE_NAME_USER = "user";

    public ShowProfileAction() throws ActionException {
        try {
            userService = new UserService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize ", e);
        }
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session= req.getSession();
        int i = (int) session.getAttribute(SESSION_PARAMETER_USER_ID);
        User user;
        try {
            user = userService.findById(i);
        } catch (ServiceException e) {
            throw new ActionException("can't get user from service", e);
        }
        req.setAttribute(ATTRIBUTE_NAME_USER, user);
        return new ActionResult(JSP_PAGE_NAME_PROFILE);
    }
}
