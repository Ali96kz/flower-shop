package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProductEditPageAction extends AbstractProduct{
    ProductService productService = new ProductService();
    StringAdapter stringAdapter = new StringAdapter();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        setValue(req);
        int id = stringAdapter.toInt(req.getParameter("id"));
        Product product = productService.findById(id);
        req.setAttribute("product", product);
        return new ActionResult("product-edit");
    }
}
