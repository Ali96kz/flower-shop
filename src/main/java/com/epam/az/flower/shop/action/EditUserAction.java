package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditUserAction extends AddUser{
    UserService userService = new UserService();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        ActionResult actionResult = validate(req);
        if(actionResult != null)
            return new ActionResult("edit-user");
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        User user ;
        try {
            user = userService.findById(userId);
        } catch (ServiceException e) {
            throw new ActionException("",  e);
        }
        user = fillUser(req, user);
        userService.update(user);
        return new ActionResult("profile", true);
    }
}
