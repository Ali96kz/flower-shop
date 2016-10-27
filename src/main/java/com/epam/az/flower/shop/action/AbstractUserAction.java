package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractUserAction implements Action {
    private static Logger logger = LoggerFactory.getLogger(AbstractUserAction.class);
    private UserService userService = new UserService();

    public void setUserBySessionId(HttpServletRequest req) throws ActionException {
        try {
            HttpSession session = req.getSession();
            int i = (int) session.getAttribute(SESSION_PARAMETER_USER_ID);
            User user = userService.findById(i);
            req.setAttribute(ATTRIBUTE_NAME_USER, user);

        } catch (ServiceException e) {
            logger.error("can't get user from service", e);
            throw new ActionException("can't get user from service", e);
        }
    }
}
