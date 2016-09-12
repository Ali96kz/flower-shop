package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserDeleteAccountAction implements Action {
    private UserService userService ;

    public UserDeleteAccountAction() throws ActionException {
        try {
            userService = new UserService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            HttpSession session = req.getSession();
            int userId = (int) session.getAttribute("userId");
            userService.delete(userId);
        } catch (ServiceException e) {
            throw new ActionException("", e);
        }
        return new ActionResult("delete-profile");
    }
}
