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
    private UserRoleService userRoleService= new UserRoleService();

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) throws ActionException {
        ActionResult actionResult = validate(request);
        if (actionResult != null) {
            return actionResult;
        }

        User user = new User();
        setUserRole(user, request);
        registerUser(request, user);
        return new ActionResult(JSP_PAGE_NAME_ADMIN, true);
    }

    @Override
    public void setUserRole(User user, HttpServletRequest request) throws ActionException {
        int userRoleId = stringAdapter.toInt(request.getParameter(ATTRIBUTE_NAME_USER_ROLE_ID));
        UserRole userRole;
        try {
            userRole = userRoleService.findById(userRoleId);
        } catch (ServiceException e) {
            throw new ActionException("can't find user role by id", e);
        }
        user.setUserRole(userRole);
    }

}

