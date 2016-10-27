package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAdminPage implements Action {
    private static Logger logger = LoggerFactory.getLogger(ShowAdminPage.class);
    private UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            List<User> users = userService.getAllActiveUsers();
            req.setAttribute(ATTRIBUTE_NAME_USERS, users);
            return new ActionResult(JSP_PAGE_NAME_ADMIN);
        } catch (ServiceException e) {
            logger.error("can't get all user", e);
            throw new ActionException("can't get all user", e);
        }
    }
}
