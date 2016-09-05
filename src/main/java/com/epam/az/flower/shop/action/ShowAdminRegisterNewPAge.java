package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.action.Action;
import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.service.UserRoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAdminRegisterNewPAge implements Action {
    UserRoleService userRoleService = new UserRoleService();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("userRoles", userRoleService.getAll());
        return new ActionResult("registration");
    }
}
