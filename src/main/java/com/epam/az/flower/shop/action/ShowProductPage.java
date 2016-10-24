package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.util.StringAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProductPage implements Action {
    private StringAdapter stringAdapter = new StringAdapter();
    private ProductService productService = new ProductService();


    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        int id = stringAdapter.toInt(request.getParameter(PARAMETER_PRODUCT_ID));
        try {
            Product product = productService.findById(id);

            request.setAttribute(ATTRIBUTE_NAME_PRODUCT, product);
            return new ActionResult(JSP_PAGE_NAME_PRODUCT_INF);
        } catch (ServiceException e) {
            throw new ActionException("can't get product from dao", e);
        }
    }
}
