package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.OriginService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowUserEditPage implements Action {
    private UserService userService = new UserService();
    private OriginService originService = new OriginService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute(SESSION_PARAMETER_USER_ID);

        try {
            User user = userService.findById(userId);
            req.setAttribute(ATTRIBUTE_NAME_ORIGIN_LIST, originService.getAll());
            req.setAttribute(ATTRIBUTE_NAME_USER, user);
        } catch (ServiceException e) {
            throw new ActionException("can't get user ", e);
        }
        return new ActionResult(JSP_PAGE_NAME_REGISTRATION);
    }
}
