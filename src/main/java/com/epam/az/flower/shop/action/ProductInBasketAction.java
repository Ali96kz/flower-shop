package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.util.StringAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProductInBasketAction implements Action {
    private ProductService productService = new ProductService();
    private Basket basket;
    private StringAdapter stringAdapter = new StringAdapter();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            HttpSession session = req.getSession();

            if (session.getAttribute(ATTRIBUTE_BASKET) == null) {
                basket = new Basket();
            }

            String productId = req.getParameter(PARAMETER_PRODUCT_ID);
            int id = stringAdapter.toInt(productId);

            basket.add(productService.findById(id));
            session.setAttribute(ATTRIBUTE_BASKET, basket);
            return new ActionResult(JSP_PAGE_NAME_VITRINE, true);
        } catch (ServiceException e) {
            throw new ActionException("can't find product by id ", e);
        }
    }
}
