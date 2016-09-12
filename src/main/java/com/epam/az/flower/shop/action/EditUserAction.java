package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditUserAction extends AddUser {
    UserService userService;

    public EditUserAction() throws ActionException {
        try {
            userService = new UserService();

        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            ActionResult actionResult = validate(req);
            if (actionResult != null)
                return new ActionResult("edit-user");
            HttpSession session = req.getSession();
            int userId = (int) session.getAttribute("userId");
            User user;

            user = userService.findById(userId);
            user = fillUser(req, user);
            userService.update(user);
        } catch (ServiceException e) {
            throw new ActionException("", e);
        }
        return new ActionResult("profile", true);
    }
}
