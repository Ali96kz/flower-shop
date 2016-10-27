package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.util.StringAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProductInBasketAction extends AbstractBasket {
    private ProductService productService = new ProductService();
    private StringAdapter stringAdapter = new StringAdapter();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            HttpSession session = req.getSession();
            Basket basket = getBasket(session);

            int productId = stringAdapter.toInt(req.getParameter(PARAMETER_PRODUCT_ID));
            basket.add(productService.findById(productId));

            session.setAttribute(ATTRIBUTE_BASKET, basket);
            return new ActionResult(JSP_PAGE_NAME_VITRINE, true);
        } catch (ServiceException e) {
            throw new ActionException("can't find product by id ", e);
        }
    }
}
