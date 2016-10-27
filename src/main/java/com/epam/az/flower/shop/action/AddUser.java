package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.dao.manager.AbstractDAO;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserRole;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserRoleService;
import com.epam.az.flower.shop.util.Hasher;
import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.util.UtilClassException;
import com.epam.az.flower.shop.validator.RegisterProfileValidator;
import com.epam.az.flower.shop.validator.Validator;
import com.epam.az.flower.shop.validator.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class AddUser implements Action {
    protected StringAdapter stringAdapter = new StringAdapter();
    protected UserRoleService userRoleService = new UserRoleService();
    private Logger logger = LoggerFactory.getLogger(AbstractDAO.class);
    private Hasher hasher = new Hasher();
    private Validator validator = new RegisterProfileValidator();

    public User fillUser(HttpServletRequest request, User user) throws ActionException {
        user.setPassword(hasher.hash(request.getParameter(PARAMETER_PASSWORD)));
        user.setFirstName(request.getParameter(PARAMETER_FIRST_NAME));
        user.setLastName(request.getParameter(PARAMETER_LAST_NAME));
        user.setNickName(request.getParameter(PARAMETER_NICK_NAME));
        try {
            user.setDateBirthday(stringAdapter.toSqlDate(request.getParameter(PARAMETER_DATE_BIRTHDAY)));
        } catch (UtilClassException e) {
            logger.error("can't parse date", e);
            throw new ActionException("can't parse date", e);
        }
        return user;
    }

    public void setUserRole(User user, HttpServletRequest request) throws ActionException {
        try {
            UserRole userRole;
            userRole = userRoleService.getUserRoleByName(ROLE_CUSTOMER);
            user.setUserRole(userRole);
        } catch (ServiceException e) {
            logger.error("can't set user role", e);
            throw new ActionException("can't set user role", e);
        }
    }

    public boolean isValidate(HttpServletRequest request) throws ActionException {
        try {
            List<String> errorMsg = validator.isValidate(request);

            if (errorMsg.size() > 0) {
                String name = request.getParameter(PARAMETER_FIRST_NAME);
                String nickName = request.getParameter(PARAMETER_NICK_NAME);
                String lastName = request.getParameter(PARAMETER_LAST_NAME);

                User user = new User();
                user.setNickName(nickName);
                user.setFirstName(name);
                user.setLastName(lastName);

                request.setAttribute(ATTRIBUTE_NAME_USER, user);
                request.setAttribute(ATTRIBUTE_ERROR_MSG, errorMsg);
                return false;
            }
            return true;
        } catch (ValidatorException e) {
            logger.error("can't validate", e);
            throw new ActionException("can't isValidate", e);
        }

    }

    public void putInSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_PARAMETER_USER_ID, user.getId());
    }
}
