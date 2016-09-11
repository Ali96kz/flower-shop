package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserRole;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserRoleService;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAddUserAction extends AddUser {
    private UserService userService = new UserService();
    UserRoleService userRoleService = new UserRoleService();

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) throws ActionException {
        ActionResult actionResult = validate(request);
        if (actionResult != null) {
            return actionResult;
        }

        User user = fillUser(request, new User());
        setUserRole(user, request);
        user = userService.registerUser(user);
        putInSession(user, request);

        return new ActionResult("admin", true);
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

