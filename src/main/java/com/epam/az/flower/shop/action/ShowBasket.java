package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.Basket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowBasket implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        if (session.getAttribute("basket") == null) {
            req.setAttribute("basket", new Basket());
            return new ActionResult("basket");
        }

        Basket basket = (Basket) session.getAttribute("basket");
        req.setAttribute("basket", basket);

        return new ActionResult("basket");
    }
}
