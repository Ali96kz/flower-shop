package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAddProductAction implements Action {
    ProductService productService = new ProductService();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("origins", productService.getAllOrigin());
        req.setAttribute("visualParameters", productService.getAllVisualParameters());
        req.setAttribute("waterInWeeks", productService.getAllWaterInWeek());
        req.setAttribute("temperature", productService.getAllTemperature());
        return new ActionResult("product-add");
    }
}
