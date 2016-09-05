package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserRole;
import com.epam.az.flower.shop.service.UserRoleService;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAddUserAction extends AddUser {
    private UserService userService = new UserService();
    UserRoleService userRoleService = new UserRoleService();

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) {
        ActionResult actionResult = validate(request);
        if (actionResult != null) {
            return actionResult;
        }

        User user = fillUser(request);
        setUserRole(user, request);
        user = userService.registerUser(user);
        putInSession(user, request);

        return new ActionResult("admin", true);
    }

    @Override
    public void setUserRole(User user, HttpServletRequest request) {
        int userRoleId = stringAdapter.toInt(request.getParameter("userRoleId"));
        UserRole userRole = userRoleService.findById(userRoleId);
        user.setUserRole(userRole);
    }

}

