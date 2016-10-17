package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserRoleService;
import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminDeleteUserAction implements Action {
    public static final String JSP_PAGE_NAME_DELETE_PROFILE = "delete-profile";
    public static final String PARAMETER_USER_ID = "id";
    private UserService userService;
    private StringAdapter stringAdapter = new StringAdapter();

    public AdminDeleteUserAction() throws ActionException {
        try {
            userService = new UserService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            int userId = stringAdapter.toInt(req.getParameter(PARAMETER_USER_ID));
            userService.delete(userId);
            return new ActionResult(JSP_PAGE_NAME_DELETE_PROFILE, true);
        } catch (ServiceException e) {
            throw new ActionException("", e);
        }

    }
}
