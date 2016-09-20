package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {
    public static final String JSP_PAGE_NAME_REGISTRATION = "registration";
    UserService userService ;
    public LogoutAction() throws ActionException {
        try {
            userService = new UserService();

        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        userService.logout((Integer) session.getAttribute("userId"));
        session.invalidate();
        return new ActionResult(JSP_PAGE_NAME_REGISTRATION, true);
    }
}
