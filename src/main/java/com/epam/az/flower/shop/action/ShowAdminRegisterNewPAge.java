package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserRoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAdminRegisterNewPAge implements Action {
    public static final String JSP_PAGE_NAME_REGISTRATION = "admin-registration";
    public static final String ATTRIBUTE_USER_ROLES = "userRoles";
    private UserRoleService userRoleService = new UserRoleService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            req.setAttribute(ATTRIBUTE_USER_ROLES, userRoleService.getAll());
        } catch (ServiceException e) {
            throw new ActionException("can't get all userROle", e);
        }
        return new ActionResult(JSP_PAGE_NAME_REGISTRATION);
    }
}
