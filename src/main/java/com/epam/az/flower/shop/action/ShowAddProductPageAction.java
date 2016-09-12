package com.epam.az.flower.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAddProductPageAction extends AbstractProduct{

    public ShowAddProductPageAction() throws ActionException {
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        setValue(req);

        return new ActionResult("product-add");
    }

}
