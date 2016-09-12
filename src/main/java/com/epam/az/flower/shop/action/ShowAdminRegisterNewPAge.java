package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserRoleService;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAdminRegisterNewPAge implements Action {
    UserRoleService userRoleService ;
    public ShowAdminRegisterNewPAge() throws ActionException {
        try {
            userRoleService= new UserRoleService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("userRoles", userRoleService.getAll());
        return new ActionResult("registration");
    }
}
