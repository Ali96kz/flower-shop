package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.validator.RegisterProfileValidator;
import com.mysql.cj.api.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RegisterAction implements Action {
    private UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        RegisterProfileValidator validator = new RegisterProfileValidator();
        StringAdapter stringAdapter = new StringAdapter();
        List<String> errorMsg = validator.isValidate(req);

        if (errorMsg.size() == 0) {
            User user = new User();

            user.setFirstName(req.getParameter("firstName"));
            user.setLastName(req.getParameter("lastName"));
            user.setNickName(req.getParameter("nickName"));
            user.setDateBirthday(stringAdapter.toSqlDate(req.getParameter("birthdayDate")));
            user.setGender(req.getParameter("gender"));


            int userId = userService.registerUser(user);
            HttpSession session = req.getSession();
            session.setAttribute("userId", userId);

            ActionResult actionResult = new ActionResult("/profile", true);
            return actionResult;
        } else {
            ActionResult actionResult = new ActionResult("registration");
            req.setAttribute("errorMsg", errorMsg);
            return actionResult;
        }
    }
}
