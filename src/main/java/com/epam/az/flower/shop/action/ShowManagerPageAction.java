package com.epam.az.flower.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowManagerPageAction extends AbstractVitrine {
    public static final String JSP_PAGE_NAME_MANAGER = "manager";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        setPaginationList(req);
        setPage(req);
        return new ActionResult(JSP_PAGE_NAME_MANAGER);
    }
}
