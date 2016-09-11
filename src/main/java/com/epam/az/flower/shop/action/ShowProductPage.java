package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProductPage implements Action {
    ProductService productService = new ProductService();
    StringAdapter stringAdapter = new StringAdapter();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        int id = stringAdapter.toInt(req.getParameter("id"));
        Product product = null;
        try {
            product = productService.findById(id);
        } catch (ServiceException e) {
            throw new ActionException("can'tget product from dao", e);
        }

        req.setAttribute("product", product);
        return new ActionResult("product-inf");
    }
}
