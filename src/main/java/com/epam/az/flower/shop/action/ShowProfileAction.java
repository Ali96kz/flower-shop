package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowProfileAction implements Action {
    private static final Logger logger = LoggerFactory.getLogger(ShowProfileAction.class);
    private UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        int i = (int) session.getAttribute(SESSION_PARAMETER_USER_ID);
        User user;

        try {
            user = userService.findById(i);
        } catch (ServiceException e) {
            logger.error("can't get user from service", e);
            throw new ActionException("can't get user from service", e);
        }

        req.setAttribute(ATTRIBUTE_NAME_USER, user);
        return new ActionResult(JSP_PAGE_NAME_PROFILE);
    }
}
