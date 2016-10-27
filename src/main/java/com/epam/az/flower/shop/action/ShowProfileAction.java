package com.epam.az.flower.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProfileAction extends AbstractUserAction {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        setUserBySessionId(req);
        return new ActionResult(JSP_PAGE_NAME_PROFILE);
    }
}
