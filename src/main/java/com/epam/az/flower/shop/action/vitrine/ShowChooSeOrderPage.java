package com.epam.az.flower.shop.action.vitrine;

import com.epam.az.flower.shop.action.Action;
import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.ActionResult;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowChooSeOrderPage implements Action {
    private UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            req.setAttribute("users", userService.getAll());
            return new ActionResult(CHOOSE_ORDER);
        } catch (ServiceException e) {
            throw new ActionException("", e);
        }
    }
}
