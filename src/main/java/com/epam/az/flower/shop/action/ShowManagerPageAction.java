package com.epam.az.flower.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowManagerPageAction extends AbstractVitrine {
    public ShowManagerPageAction() throws ActionException {
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        setPaginationList(req);
        return new ActionResult("manager");
    }
}
