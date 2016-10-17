package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProductInBasketAction implements Action{
    public static final String JSP_PAGE_NAME_VITRINE = "vitrine";
    public static final String ATTRIBUTE_NAME_BASKET = "basket";
    public static final String PARAMETER_PRODUCT_ID = "productId";
    public static final String ATTRIBUTE_BASKET = "basket";
    ProductService productService;
    Basket basket;
    StringAdapter stringAdapter = new StringAdapter();
    public ProductInBasketAction() throws ActionException {
        try {
            productService = new ProductService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();

        if (session.getAttribute(ATTRIBUTE_NAME_BASKET) == null) {
             basket = new Basket();
        }

        String productId = req.getParameter(PARAMETER_PRODUCT_ID);
        int id = stringAdapter.toInt(productId);
        try {
            basket.add(productService.findById(id));
        } catch (ServiceException e) {
            throw new ActionException("can't find productby id ", e);
        }
        session.setAttribute(ATTRIBUTE_BASKET, basket);
        return new ActionResult(JSP_PAGE_NAME_VITRINE, true);
    }
}
