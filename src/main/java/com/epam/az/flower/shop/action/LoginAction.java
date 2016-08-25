package com.epam.az.flower.shop.action;


import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.validator.LogInValidator;
import com.epam.az.flower.shop.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginAction implements Action {
    Logger log = LoggerFactory.getLogger(LoginAction.class);
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        Integer id = (Integer) session.getAttribute("userId");

        Validator validator = new LogInValidator();
        List<String> errorMsg = validator.isValidate(req);
        UserService userService = new UserService();

        if (errorMsg.size() == 0) {
            String nickName = req.getParameter("nickName");
            String password = req.getParameter("password");

            int userId = userService.getUserByCredentials(nickName, password);

            session.setAttribute("userId", userId);
            return new ActionResult("profile", true);
        } else {
            req.setAttribute("errorMsg", errorMsg);
            return new ActionResult("login");
        }
    }
}
