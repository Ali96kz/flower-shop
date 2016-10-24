package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.validator.AddProductValidator;
import com.epam.az.flower.shop.validator.Validator;
import com.epam.az.flower.shop.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddProductAction extends AbstractProduct {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        if (isValidate(req)) {
            setValue(req);
            return new ActionResult(JSP_PAGE_NAME_PRODUCT_ADD);
        }

        Product product = getProduct(req, new Product());
        int productId ;
        try {
            productId = productService.addNewProduct(product);
        } catch (ServiceException e) {
            throw new ActionException("can't add product to data", e);
        }
        return new ActionResult(JSP_PAGE_PRODUCT_INF + productId, true);
    }

    public boolean isValidate(HttpServletRequest req) throws ActionException {
        Validator validator = new AddProductValidator();
        List<String> errorMsg;
        try {
            errorMsg = validator.isValidate(req);
        } catch (ValidatorException e) {
            throw new ActionException("problem with product validator", e);
        }
        if (errorMsg.size() > 0) {
            req.setAttribute(ATTRIBUTE_NAME_ERROR_MSG, errorMsg);
            return false;
        }
        return true;
    }
}
