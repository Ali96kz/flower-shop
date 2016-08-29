package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowBasketAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("basket") == null) {
            Basket basket = new Basket();
            req.setAttribute("basket", basket);
        } else {
            Basket basket = (Basket) session.getAttribute("basket");
            req.setAttribute("basket", basket);

            int summ = 0;
            for (Product product : basket.getProducts()) {
                summ += product.getPrice();
            }

            req.setAttribute("bill", summ);
            req.setAttribute("basket", basket);
            return new ActionResult("basket");
        }
        return new ActionResult("basket");
    }
}
