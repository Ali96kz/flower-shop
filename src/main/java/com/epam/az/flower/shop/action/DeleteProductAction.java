package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductAction implements Action {
    public static final String JSP_PAGE_NAME_MANAGER = "manager";
    StringAdapter stringAdapter = new StringAdapter();
    ProductService productService;

    public DeleteProductAction() throws ActionException {
        try {
            productService = new ProductService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            int productId = stringAdapter.toInt(req.getParameter("productId"));
            productService.deleteProduct(productId);
            return new ActionResult(JSP_PAGE_NAME_MANAGER, true);
        } catch (ServiceException e) {
            throw new ActionException("can't execute delete action", e);
        }
    }
}
