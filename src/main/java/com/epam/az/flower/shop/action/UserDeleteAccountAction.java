package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserDeleteAccountAction implements Action {
    public static final String USER_ID = "userId";
    public static final String PAGE_DELETE_PROFILE = "delete-profile";
    private UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            HttpSession session = req.getSession();
            int userId = (int) session.getAttribute(USER_ID);
            userService.delete(userId);
        } catch (ServiceException e) {
            throw new ActionException("can't find user by id", e);
        }
        return new ActionResult(PAGE_DELETE_PROFILE);
    }
}
