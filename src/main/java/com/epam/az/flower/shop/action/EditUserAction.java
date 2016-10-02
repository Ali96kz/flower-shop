package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditUserAction extends AddUser {
    public static final String JSP_PAGE_NAME_PROFILE = "profile";
    public static final String JSP_PAGE_NAME_EDIT_USER = "edit-user";
    public static final String ATTRIBUTE_NAME_USER_ID = "userId";
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
            boolean isValidate = validate(req);
            if (isValidate == false)
                return new ActionResult(JSP_PAGE_NAME_EDIT_USER);
            HttpSession session = req.getSession();
            int userId = (int) session.getAttribute(ATTRIBUTE_NAME_USER_ID);
            User user;

            user = userService.findById(userId);
            user = fillUser(req, user);
            userService.update(user);
        } catch (ServiceException e) {
            throw new ActionException("can't find user by id", e);
        }
        return new ActionResult(JSP_PAGE_NAME_PROFILE, true);
    }
}
