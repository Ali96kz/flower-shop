package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.util.Hasher;
import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.util.validator.RegisterProfileValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RegisterAction implements Action {
    private UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) {
        RegisterProfileValidator validator = new RegisterProfileValidator();
        StringAdapter stringAdapter = new StringAdapter();
        List<String> errorMsg = validator.isValidate(request);
        Hasher hasher = new Hasher();

        if (errorMsg.size() == 0) {
            HttpSession session = request.getSession();
            User user = new User();
            user.setPassword(hasher.hash(request.getParameter("password")));
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setNickName(request.getParameter("nickName"));
            user.setDateBirthday(stringAdapter.toSqlDate(request.getParameter("dateBirthday")));
            int userId = userService.registerUser(user);

            session.setAttribute("userId", userId);
            ActionResult actionResult = new ActionResult("profile", true);

            return actionResult;
        } else {
            ActionResult actionResult = new ActionResult("registration");
            String name = request.getParameter("firstName");
            String nickName = request.getParameter("nickName");
            String lastName = request.getParameter("lastName");

            User user = new User();
            user.setNickName(nickName);
            user.setFirstName(name);
            user.setLastName(lastName);

            request.setAttribute("user", user);
            request.setAttribute("errorMsg", errorMsg);
            return actionResult;
        }
    }
}
