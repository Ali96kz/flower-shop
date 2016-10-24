package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.util.StringAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProductPage implements Action {
    private static final Logger logger = LoggerFactory.getLogger(ShowProductPage.class);
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
            logger.error("can't get product from dao", e);
            throw new ActionException("can't get product from dao", e);
        }
    }
}
