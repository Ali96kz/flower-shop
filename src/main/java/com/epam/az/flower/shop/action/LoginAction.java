package com.epam.az.flower.shop.action;


import com.epam.az.flower.shop.entity.ActionResult;
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
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        Validator validator = new LogInValidator();
        List<String> errorMsg = validator.isValidate(req);
        UserService userService = new UserService();
        if (errorMsg.size() > 0) {
            req.setAttribute("errorMsg", errorMsg);
            System.out.println(errorMsg.get(0));
            return new ActionResult("login");
        }
        String nickName = req.getParameter("nickName");
        String password = req.getParameter("password");

        int userId = userService.getUserByCredentials(nickName, password);

        session.setAttribute("userId", userId);
        return new ActionResult("profile", true);
        }
    Logger log = LoggerFactory.getLogger(LoginAction.class);
}
