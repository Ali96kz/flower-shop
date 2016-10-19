package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserRole;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserRoleService;
import com.epam.az.flower.shop.util.Hasher;
import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.validator.RegisterProfileValidator;
import com.epam.az.flower.shop.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class AddUser implements Action {
    public static final String JSP_PAGE_NAME_REGISTRATION = "registration";
    public static final String ATTRIBUTE_NAME_USER = "user";
    private Hasher hasher = new Hasher();
    protected StringAdapter stringAdapter = new StringAdapter();
    protected UserRoleService userRoleService = new UserRoleService();

    public static final String ATTRIBUTE_NAME_USER_ID = "userId";
    public static final String ATTRIBUTE_NAME_ERROR_MSG = "errorMsg";

    public static final String PARAMETER_FIRST_NAME = "firstName";
    public static final String PARAMETER_NICK_NAME = "nickName";
    public static final String PARAMETER_LAST_NAME = "lastName";
    public static final String PARAMETER_DATE_BIRTHDAY = "dateBirthday";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_CONFIRM_PASSWORD = "confirmPassword";
    public static final String ROLE_CUSTOMER = "customer";

    public User fillUser(HttpServletRequest request, User user) {
        user.setPassword(hasher.hash(request.getParameter(PARAMETER_PASSWORD)));
        user.setFirstName(request.getParameter(PARAMETER_FIRST_NAME));
        user.setLastName(request.getParameter(PARAMETER_LAST_NAME));
        user.setNickName(request.getParameter(PARAMETER_NICK_NAME));
        user.setDateBirthday(stringAdapter.toSqlDate(request.getParameter(PARAMETER_DATE_BIRTHDAY)));

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

    public boolean validate(HttpServletRequest request) throws ActionException {
        RegisterProfileValidator validator;
        try {
            validator = new RegisterProfileValidator();
        } catch (ValidatorException e) {
            throw new ActionException("can;t create validator", e);
        }

        List<String> errorMsg;
        try {
            errorMsg = validator.isValidate(request);
        } catch (ValidatorException e) {
            throw new ActionException("can't validate", e);
        }

        if (errorMsg.size() > 0) {
            String name = request.getParameter(PARAMETER_FIRST_NAME);
            String nickName = request.getParameter(PARAMETER_NICK_NAME);
            String lastName = request.getParameter(PARAMETER_LAST_NAME);

            User user = new User();
            user.setNickName(nickName);
            user.setFirstName(name);
            user.setLastName(lastName);

            request.setAttribute(ATTRIBUTE_NAME_USER, user);
            request.setAttribute(ATTRIBUTE_NAME_ERROR_MSG, errorMsg);
            return false;
        }
        return true;
    }

    public void putInSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(ATTRIBUTE_NAME_USER_ID, user.getId());
    }
}
