package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.validator.AddProductValidator;
import com.epam.az.flower.shop.validator.Validator;
import com.epam.az.flower.shop.validator.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddProductAction extends AbstractProduct {
    private Logger logger = LoggerFactory.getLogger(AddProductAction.class);
    private Validator validator = new AddProductValidator();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        if (!isValidate(req)) {
            setValue(req);
            return ACTION_RESULT_ADD_PRODUCT_REDIRECT_FALSE;
        }

        try {
            Product product = getProduct(req, new Product());
            int productId = productService.addNewProduct(product);
            return new ActionResult(JSP_PAGE_PRODUCT_INF + productId, true);
        } catch (ServiceException e) {
            logger.error("can't add product", e);
            throw new ActionException("can't add product to data", e);
        }
    }

    public boolean isValidate(HttpServletRequest req) throws ActionException {
        List<String> errorMsg;
        try {
            errorMsg = validator.isValidate(req);
        } catch (ValidatorException e) {
            logger.error("can't validate object", e);
            throw new ActionException("problem with product validator", e);
        }

        if (errorMsg.size() > 0) {
            req.setAttribute(ATTRIBUTE_ERROR_MSG, errorMsg);
            return false;
        }

        return true;
    }
}
