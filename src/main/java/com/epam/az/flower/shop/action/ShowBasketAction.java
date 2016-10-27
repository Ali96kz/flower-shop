package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Basket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowBasketAction extends AbstractBasket {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Basket basket = getBasket(req.getSession());

        req.setAttribute(ATTRIBUTE_BASKET, basket);
        req.setAttribute(ATTRIBUTE_NAME_BILL, basket.getSum());
        req.setAttribute(ATTRIBUTE_BASKET, basket);
        return new ActionResult(JSP_PAGE_NAME_BASKET);
    }
}

