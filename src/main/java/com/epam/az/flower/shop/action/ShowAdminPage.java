package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAdminPage implements Action {
    private static final String JSP_PAGE_NAME_ADMIN = "admin";
    private static final String ATTRIBUTE_NAME_USERS = "users";
    private UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            List<User> users = userService.getAllActiveUsers();
            req.setAttribute(ATTRIBUTE_NAME_USERS, users);
            return new ActionResult(JSP_PAGE_NAME_ADMIN);
        } catch (ServiceException e) {
            throw new ActionException("can't get all user", e);
        }
    }
}
