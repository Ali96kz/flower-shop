package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductAction implements Action {
    StringAdapter stringAdapter = new StringAdapter();
    ProductService productService = new ProductService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int productId = stringAdapter.toInt(req.getParameter("id"));
        productService.deleteProduct(productId);
        return new ActionResult("manager", true);
    }
}
