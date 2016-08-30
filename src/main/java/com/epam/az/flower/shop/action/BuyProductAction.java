package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyProductAction implements Action {
    StringAdapter stringAdapter = new StringAdapter();
    ProductService productService = new ProductService();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int productId = stringAdapter.toInt(req.getParameter("productId"));
        Product product = productService.findById(productId);
        req.setAttribute("price", product.getPrice());
        return new ActionResult("bill");
    }
}
