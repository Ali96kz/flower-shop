package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.entity.Basket;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteProductFromBasket implements Action {
    public static final String JSP_PAGE_NAME_BASKET = "basket";
    public static final String ATTRIBUTE_BASKET = "basket";
    public static final String PARAMETER_PRODUCT_ID = "id";
    private StringAdapter stringAdapter = new StringAdapter();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Basket basket = (Basket) session.getAttribute(ATTRIBUTE_BASKET);
        int productId = stringAdapter.toInt(req.getParameter(PARAMETER_PRODUCT_ID));
        basket.delete(productId);
        return new ActionResult(JSP_PAGE_NAME_BASKET, true);
    }
}

