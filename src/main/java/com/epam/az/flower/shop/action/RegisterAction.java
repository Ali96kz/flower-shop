package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction implements Action {
    private UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User();
        user.setId(63);
        user.setFirstName(req.getParameter("user.firstName"));
        user.setLastName(req.getParameter("user.lastName"));
        user.setNickName(req.getParameter("user.nickName"));
        user.setDateBirthday(StringAdapter.toSqlDate(req.getParameter("user.birthdayDate")));

        int userID = userService.registerUser(user);
        ActionResult actionResult;

        if (userID > 0) {
            actionResult = new ActionResult("/profile", true);
        } else {
            actionResult = new ActionResult("registration");
        }

        return actionResult;
    }
}
