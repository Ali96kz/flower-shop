package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserDeleteAccountAction implements Action {
    private static final Logger logger = LoggerFactory.getLogger(UserDeleteAccountAction.class);
    private UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            HttpSession session = req.getSession();
            int userId = (int) session.getAttribute(SESSION_PARAMETER_USER_ID);
            userService.delete(userId);
            return new ActionResult(PAGE_DELETE_PROFILE);
        } catch (ServiceException e) {
            logger.error("can't find user by id", e);
            throw new ActionException("can't find user by id", e);
        }
    }
}
