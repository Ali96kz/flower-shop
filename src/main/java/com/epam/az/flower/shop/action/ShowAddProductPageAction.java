package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAddProductPageAction extends AbstractProduct{

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        setValue(req);

        return new ActionResult("product-add");
    }

}
