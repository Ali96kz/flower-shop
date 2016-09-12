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
    private UserService userService;
    private OriginService originService;

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
        Integer userId = (Integer) session.getAttribute("userId");
        User user;
        try {
            user = userService.findById(userId);

        } catch (ServiceException e) {
            throw new ActionException("can't get user ", e);
        }
        req.setAttribute("origins", originService.getAllOrigin());
        req.setAttribute("user", user);
        return new ActionResult("registration");
    }
}
