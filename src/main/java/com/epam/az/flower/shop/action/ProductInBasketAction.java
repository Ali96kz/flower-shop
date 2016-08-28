package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProductInBasketAction implements Action{
    ProductService productService = new ProductService();
    Basket basket;
    StringAdapter stringAdapter = new StringAdapter();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        if (session.getAttribute("basket") == null) {
             basket = new Basket();
        }

        String productId = req.getParameter("productId");
        int id = stringAdapter.toInt(productId);
        basket.add(productService.findById(id));
        session.setAttribute("basket", basket);
        return new ActionResult("vitrine", true);
    }
}