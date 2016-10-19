package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.util.StringAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminDeleteUserAction implements Action {
    private static final String JSP_PAGE_NAME_DELETE_PROFILE = "delete-profile";
    private static final String PARAMETER_USER_ID = "id";
    private UserService userService = new UserService();
    private StringAdapter stringAdapter = new StringAdapter();


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
