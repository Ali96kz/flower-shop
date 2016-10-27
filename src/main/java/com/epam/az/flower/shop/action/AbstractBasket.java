package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Basket;

import javax.servlet.http.HttpSession;

public abstract class AbstractBasket implements Action {
    public Basket getBasket(HttpSession session) {
        Basket basket;

        if (session.getAttribute(ATTRIBUTE_BASKET) == null) {
            basket = new Basket();
        } else {
            basket = (Basket) session.getAttribute(ATTRIBUTE_BASKET);
        }

        return basket;
    }
}
