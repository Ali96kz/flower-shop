package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProductEditPageAction extends AbstractProduct{
    public static final String JSP_PAGE_NAME_PRODUCT_EDIT = "product-edit";
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";
    ProductService productService;

    public ShowProductEditPageAction() throws ActionException {
        try {
            productService = new ProductService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }

    StringAdapter stringAdapter = new StringAdapter();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        setValue(req);
        int id = stringAdapter.toInt(req.getParameter(ATTRIBUTE_NAME_PRODUCT_ID));
        Product product ;
        try {
            product = productService.findById(id);
        } catch (ServiceException e) {
            throw new ActionException("can;t get product by id from service", e);
        }
        req.setAttribute(ATTRIBUTE_NAME_PRODUCT, product);
        return new ActionResult(JSP_PAGE_NAME_PRODUCT_EDIT);
    }
}

