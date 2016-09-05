package com.epam.az.flower.shop.action.product;

import com.epam.az.flower.shop.action.Action;
import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.Origin;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.VisualParameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditProductAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Product product = new Product();
        Origin origin = new Origin();
        VisualParameters visualParameters = new VisualParameters();
        req.getParameter("country");
        return null;
    }
}
