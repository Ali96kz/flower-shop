package com.epam.az.flower.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProductEditPageAction extends AbstractProduct {
    public static final String JSP_PAGE_NAME_PRODUCT_EDIT = "product-edit";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        setValue(req);
        setProduct(req);
        return new ActionResult(JSP_PAGE_NAME_PRODUCT_EDIT);
    }
}

