package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowCash implements Action {
    private UserService userService = new UserService();
    private static final Logger logger = LoggerFactory.getLogger(ShowCash.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            HttpSession session = req.getSession();
            int userId = (int) session.getAttribute(SESSION_PARAMETER_USER_ID);
            User user = userService.findById(userId);
            req.setAttribute(ATTRIBUTE_NAME_USER, user);
            return new ActionResult(JSP_PAGE_NAME_CASH);
        } catch (ServiceException e) {
            logger.error("can't get user by id from service", e);
            throw new ActionException("can't get user by id from service", e);
        }
    }
}
