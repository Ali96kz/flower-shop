package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.util.StringAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductAction implements Action {
    private static Logger logger = LoggerFactory.getLogger(DeleteProductAction.class);
    private StringAdapter stringAdapter = new StringAdapter();
    private ProductService productService = new ProductService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            int productId = stringAdapter.toInt(req.getParameter(PARAMETER_PRODUCT_ID));
            productService.deleteProduct(productId);
            return new ActionResult(JSP_PAGE_NAME_MANAGER, true);
        } catch (ServiceException e) {
            logger.error("can't execute action", e);
            throw new ActionException("can't execute delete action", e);
        }
    }
}
