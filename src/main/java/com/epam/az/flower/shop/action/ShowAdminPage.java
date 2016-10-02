package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAdminPage implements Action {
    public static final String JSP_PAGE_NAME_ADMIN = "admin";
    private UserService userService;

    public ShowAdminPage() throws ActionException {
        try {
            userService = new UserService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        List<User> users ;
        try {
            users = userService.getAllActiveUsers();
        } catch (ServiceException e) {
            throw new ActionException("can't get all user", e);
        }

        req.setAttribute("users", users);
        return new ActionResult(JSP_PAGE_NAME_ADMIN);
    }
}
