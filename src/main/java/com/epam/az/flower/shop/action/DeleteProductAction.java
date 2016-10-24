package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.util.StringAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductAction implements Action {
    private StringAdapter stringAdapter = new StringAdapter();
    private ProductService productService = new ProductService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            int productId = stringAdapter.toInt(req.getParameter(PARAMETER_PRODUCT_ID));
            productService.deleteProduct(productId);
            return new ActionResult(JSP_PAGE_NAME_MANAGER, true);
        } catch (ServiceException e) {
            throw new ActionException("can't execute delete action", e);
        }
    }
}
