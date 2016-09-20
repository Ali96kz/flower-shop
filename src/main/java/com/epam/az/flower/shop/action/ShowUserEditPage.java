package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Origin;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.OriginService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowUserEditPage implements Action {
    public static final String ATTRIBUTE_NAME_ORIGIN_LIST = "origins";
    public static final String ATTRIBUTE_NAME_USER = "user";
    public static final String JSP_PAGE_NAME_REGISTRATION = "registration";
    private UserService userService;
    private OriginService originService;
    public static final String SESSION_PARAMETER_USER_ID = "userId";

    public ShowUserEditPage() throws ActionException {
        try {
            originService = new OriginService();
            userService = new UserService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute(SESSION_PARAMETER_USER_ID);
        User user;
        try {
            user = userService.findById(userId);
            req.setAttribute(ATTRIBUTE_NAME_ORIGIN_LIST, originService.getAll());
            req.setAttribute(ATTRIBUTE_NAME_USER, user);
        } catch (ServiceException e) {
            throw new ActionException("can't get user ", e);
        }
        return new ActionResult(JSP_PAGE_NAME_REGISTRATION);
    }
}
