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
    public static final String JSP_PAGE_NAME_ADMIN = "admin";
    public static final String ATTRIBUTE_NAME_USER_ROLE_ID = "userRoleId";
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
        boolean isValidate = validate(request);
        if (isValidate == false) {
            return new ActionResult("admin-registration");
        }
        try {
            User user = fillUser(request, new User());
            setUserRole(user, request);
            userService.registerUser(user);

            return new ActionResult(JSP_PAGE_NAME_ADMIN, true);
        } catch (ServiceException e) {
            throw new ActionException("can't execute action", e);
        }

    }

    @Override
    public void setUserRole(User user, HttpServletRequest request) throws ActionException {
        int userRoleId = stringAdapter.toInt(request.getParameter(ATTRIBUTE_NAME_USER_ROLE_ID));
        UserRole userRole ;
        try {
            userRole = userRoleService.findById(userRoleId);
        } catch (ServiceException e) {
            throw new ActionException("can't find user by id", e);
        }
        user.setUserRole(userRole);
    }

}

