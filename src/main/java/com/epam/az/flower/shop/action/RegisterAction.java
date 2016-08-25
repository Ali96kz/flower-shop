package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.Hasher;
import com.epam.az.flower.shop.Main;
import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.validator.RegisterProfileValidator;
import com.mysql.cj.api.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;

public class RegisterAction implements Action {
    private UserService userService = new UserService();
    private static final Logger log = LoggerFactory.getLogger(String.valueOf(Main.class));

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        RegisterProfileValidator validator = new RegisterProfileValidator();
        StringAdapter stringAdapter = new StringAdapter();
        List<String> errorMsg = validator.isValidate(req);
        Hasher hasher = new Hasher();

        if (errorMsg.size() == 0) {
            System.out.println("was");
            User user = new User();
            user.setPassword(hasher.hash(req.getParameter("password")));
            user.setFirstName(req.getParameter("firstName"));
            user.setLastName(req.getParameter("lastName"));
            user.setNickName(req.getParameter("nickName"));
            user.setDateBirthday(stringAdapter.toSqlDate(req.getParameter("birthdayDate")));

            int userId = userService.registerUser(user);
            System.out.println(userId+" = id");
            HttpSession session = req.getSession();
            session.setAttribute("userId", userId);
            ActionResult actionResult = new ActionResult("profile", true);

            return actionResult;
        } else {
            ActionResult actionResult = new ActionResult("registration");
            req.setAttribute("errorMsg", errorMsg);
            return actionResult;
        }
    }
}
