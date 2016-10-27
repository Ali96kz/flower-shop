package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditUserAction extends AddUser {
    private static final Logger logger = LoggerFactory.getLogger(EditUserAction.class);
    private UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            if (!isValidate(req))
                return new ActionResult(JSP_PAGE_NAME_EDIT_USER);

            HttpSession session = req.getSession();
            int userId = (int) session.getAttribute(SESSION_PARAMETER_USER_ID);
            User user = userService.findById(userId);
            user = fillUser(req, user);
            userService.update(user);

            return new ActionResult(JSP_PAGE_NAME_PROFILE, true);
        } catch (ServiceException e) {
            logger.error("", e);
            throw new ActionException("can't find user by id", e);
        }
    }
}
