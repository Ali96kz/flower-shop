package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {
    private UserService userService = new UserService();
    private Logger logger = LoggerFactory.getLogger(LogoutAction.class);
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute(SESSION_PARAMETER_USER_ID);
        System.out.println("user with id {} logout"+ userId.intValue());


        try {
            userService.logout(userId.intValue());
        } catch (ServiceException e) {

            throw new ActionException("", e);
        }
        session.invalidate();

        return new ActionResult(JSP_PAGE_NAME_REGISTRATION, true);
    }
}
