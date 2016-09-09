package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserRole;
import com.epam.az.flower.shop.util.Hasher;
import com.epam.az.flower.shop.validator.RegisterProfileValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class AddUser implements Action {
    private Hasher hasher = new Hasher();
    protected StringAdapter stringAdapter = new StringAdapter();

    public User fillUser(HttpServletRequest request) {
        User user = new User();
        user.setPassword(hasher.hash(request.getParameter("password")));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setNickName(request.getParameter("nickName"));
        user.setDateBirthday(stringAdapter.toSqlDate(request.getParameter("dateBirthday")));
        return user;
    }

    public void setUserRole(User user, HttpServletRequest request) {
            UserRole userRole = new UserRole();
            userRole.setId(4);
            user.setUserRole(userRole);
    }

    public ActionResult validate(HttpServletRequest request) {
        RegisterProfileValidator validator = new RegisterProfileValidator();
        List<String> errorMsg = validator.isValidate(request);

        if (errorMsg.size() > 0) {
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
            return new ActionResult("registration", true);
        }
        return null;
    }

    public void putInSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
    }
}
