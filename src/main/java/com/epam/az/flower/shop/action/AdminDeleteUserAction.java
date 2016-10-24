package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.util.StringAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminDeleteUserAction implements Action {
    private static Logger logger = LoggerFactory.getLogger(AdminDeleteUserAction.class);
    private UserService userService = new UserService();
    private StringAdapter stringAdapter = new StringAdapter();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            int userId = stringAdapter.toInt(req.getParameter(PARAMETER_USER_ID));
            userService.delete(userId);
            return new ActionResult(JSP_PAGE_NAME_DELETE_PROFILE, true);
        } catch (ServiceException e) {
            logger.error("can't delete object", e);
            throw new ActionException("can't delete user", e);
        }

    }
}
