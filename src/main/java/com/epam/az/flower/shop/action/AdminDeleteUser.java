package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.util.StringAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminDeleteUser implements Action {
    private UserService userService = new UserService();
    private StringAdapter stringAdapter = new StringAdapter();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        int userId = stringAdapter.toInt(req.getParameter("id"));
        userService.delete(userId);
        return null;
    }
}
