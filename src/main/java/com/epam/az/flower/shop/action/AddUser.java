package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserRoleService;
import com.epam.az.flower.shop.util.StringAdapter;
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
    protected UserRoleService userRoleService;
    private final String ROLE_CUSTOMER= "customer";
    public AddUser() throws ActionException {
        try {
            userRoleService = new UserRoleService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }

    }

    public User fillUser(HttpServletRequest request, User user) {
        user.setPassword(hasher.hash(request.getParameter("password")));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setNickName(request.getParameter("nickName"));
        user.setDateBirthday(stringAdapter.toSqlDate(request.getParameter("dateBirthday")));

        return user;
    }

    public void setUserRole(User user, HttpServletRequest request) throws ActionException {
        try {
            UserRole userRole;
            userRole = userRoleService.getUserRoleByName(ROLE_CUSTOMER);
            user.setUserRole(userRole);
        } catch (ServiceException e) {
            throw new ActionException("can't set user role", e);
        }
    }

    public ActionResult validate(HttpServletRequest request) {
        RegisterProfileValidator validator = new RegisterProfileValidator();
        List<String> errorMsg = validator.isValidate(request);

        if (errorMsg.size() > 0) {
            String name = request.getParameter("firstName");
            String nickName = request.getParameter("nickName");
            String lastName = request.getParameter("lastName");

            User user = new User();
            user.setNickName(nickName);
            user.setFirstName(name);
            user.setLastName(lastName);

            request.setAttribute("user", user);
            request.setAttribute("errorMsg", errorMsg);
            return new ActionResult("registration");
        }
        return null;
    }

    public void putInSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
    }
}
