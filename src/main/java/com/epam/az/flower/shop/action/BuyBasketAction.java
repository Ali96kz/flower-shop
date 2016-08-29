package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BuyBasketAction implements Action{
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getParameter("productId") != null){
            return new ActionResult("bill");
        }
        HttpSession session = req.getSession();
        Basket basket = (Basket) session.getAttribute("basket");
        int summ = 0;
        for (Product product : basket.getProducts()) {
            summ += product.getPrice();
        }
        req.setAttribute("summ", summ);
        return new ActionResult("bill");
    }
}
