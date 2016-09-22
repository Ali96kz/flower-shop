package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProductPage implements Action {
    public static final String PARAMETER_PRODUCT_ID = "id";
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";
    public static final String JSP_PAGE_NAME_PRODUCT_INF = "product-inf";
    private ProductService productService;

    public ShowProductPage() {
        productService = new ProductService();
    }

    StringAdapter stringAdapter = new StringAdapter();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        int id = stringAdapter.toInt(req.getParameter(PARAMETER_PRODUCT_ID));
        Product product;
        try {
            product = productService.findById(id);
        } catch (ServiceException e) {
            throw new ActionException("can't get product from dao", e);
        }

        req.setAttribute(ATTRIBUTE_NAME_PRODUCT, product);
        return new ActionResult(JSP_PAGE_NAME_PRODUCT_INF);
    }
}
