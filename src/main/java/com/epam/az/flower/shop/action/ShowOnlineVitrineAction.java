package com.epam.az.flower.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowOnlineVitrineAction extends AbstractVitrine{

    public static final String JSP_PAGE_NAME_VITRINE = "vitrine";

    public ShowOnlineVitrineAction() throws ActionException {
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        setPaginationList(req);
        setPage(req);
        return new ActionResult(JSP_PAGE_NAME_VITRINE);
    }
}
