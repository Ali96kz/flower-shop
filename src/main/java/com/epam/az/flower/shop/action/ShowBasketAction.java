package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowBasketAction implements Action {

    private static final String ATTRIBUTE_NAME_BASKET = "basket";
    private static final String JSP_PAGE_NAME_BASKET = "basket";
    private static final String ATTRIBUTE_NAME_BILL = "bill";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute(ATTRIBUTE_NAME_BASKET) == null) {
            Basket basket = new Basket();
            req.setAttribute(ATTRIBUTE_NAME_BASKET, basket);
        } else {
            Basket basket = (Basket) session.getAttribute(ATTRIBUTE_NAME_BASKET);
            req.setAttribute(ATTRIBUTE_NAME_BASKET, basket);

            int summ = 0;
            for (Product product : basket.getProducts()) {
                summ += product.getPrice();
            }

            req.setAttribute(ATTRIBUTE_NAME_BILL, summ);
            req.setAttribute(ATTRIBUTE_NAME_BASKET, basket);
            return new ActionResult(JSP_PAGE_NAME_BASKET);
        }
        return new ActionResult(JSP_PAGE_NAME_BASKET);
    }
}
