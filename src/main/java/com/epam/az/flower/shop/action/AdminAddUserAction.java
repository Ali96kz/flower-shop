package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserRole;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserRoleService;
import com.epam.az.flower.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAddUserAction extends AddUser {
    private static final Logger log = LoggerFactory.getLogger(AdminAddUserAction.class);
    private UserService userService = new UserService();
    private UserRoleService userRoleService = new UserRoleService();

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) throws ActionException {
        boolean isValidate = isValidate(request);
        if (!isValidate) {
            return new ActionResult(JSP_PAGE_ADMIN_REGISTRATION);
        }
        try {
            User user = fillUser(request, new User());
            setUserRole(user, request);
            userService.registerUser(user);

            return new ActionResult(JSP_PAGE_NAME_ADMIN, true);
        } catch (ServiceException e) {
            log.error("can't get user from service", e);
            throw new ActionException("can't execute action", e);
        }

    }

    @Override
    public void setUserRole(User user, HttpServletRequest request) throws ActionException {
        int userRoleId = stringAdapter.toInt(request.getParameter(ATTRIBUTE_NAME_USER_ROLE_ID));
        UserRole userRole;
        try {
            userRole = userRoleService.findById(userRoleId);
        } catch (ServiceException e) {
            log.error("can't get userRole from service", e);

            throw new ActionException("can't find user by id", e);
        }
        user.setUserRole(userRole);
    }

}

