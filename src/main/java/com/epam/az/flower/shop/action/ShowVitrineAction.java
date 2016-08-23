package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowVitrineAction implements Action{
    ProductService productService = new ProductService();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> products = productService.getAllProduct();
        ActionResult actionResult = new ActionResult("vitrine");
        req.setAttribute("products", products);
        return actionResult;
    }
}
