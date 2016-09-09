package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.Basket;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteProductFromBasket implements Action {
    StringAdapter stringAdapter = new StringAdapter();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Basket basket = (Basket) session.getAttribute("basket");
        int productId = stringAdapter.toInt(req.getParameter("id"));
        basket.delete(productId);
        return new ActionResult("basket", true);
    }
}

