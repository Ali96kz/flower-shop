package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserRole;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserRoleService;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAddUserAction extends AddUser {
    private UserService userService;
    UserRoleService userRoleService;

    public AdminAddUserAction() throws ActionException {
        try {
            userRoleService = new UserRoleService();
            userService = new UserService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) throws ActionException {
        ActionResult actionResult = validate(request);
        if (actionResult != null) {
            return actionResult;
        }
        try {
            User user = fillUser(request, new User());
            setUserRole(user, request);
            user = userService.registerUser(user);
            putInSession(user, request);
            return new ActionResult("admin", true);
        } catch (ServiceException e) {
            throw new ActionException("can't execute action", e);
        }

    }

    @Override
    public void setUserRole(User user, HttpServletRequest request) throws ActionException {
        int userRoleId = stringAdapter.toInt(request.getParameter("userRoleId"));
        UserRole userRole = null;
        try {
            userRole = userRoleService.findById(userRoleId);
        } catch (ServiceException e) {
            throw new ActionException("can't find user by id", e);
        }
        user.setUserRole(userRole);
    }

}

