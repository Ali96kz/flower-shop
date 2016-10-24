package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Flower;
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

public class EditProductAction extends AbstractProduct {
    private static final Logger logger = LoggerFactory.getLogger(EditProductAction.class);
    private Validator validator = new AddProductValidator();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            if (!isValidate(req)) {
                setValue(req);
                setProduct(req);
                return new ActionResult(JSP_PAGE_NAME_EDIT_PRODUCT);
            }

            int productId = stringAdapter.toInt(req.getParameter(PARAMETER_PRODUCT_ID));
            Flower flower = productService.findById(productId).getFlower();
            Product product = getProduct(req, new Product());
            product.getFlower().setId(flower.getId());
            product.setId(productId);
            productService.update(product);

            return new ActionResult(JSP_PAGE_NAME_PRODUCT + ATTRIBUTE_NAME_PRODUCT_ID + productId, true);
        } catch (ServiceException e) {
            logger.error("can't get user by id", e);
            throw new ActionException("can't get user by id", e);
        }
    }

    public boolean isValidate(HttpServletRequest req) throws ActionException {
        List<String> errorMsg;
        try {
            errorMsg = validator.isValidate(req);
            if (errorMsg.size() > 0) {
                req.setAttribute(ATTRIBUTE_NAME_ERROR_MSG, errorMsg);
                return false;
            }
            return true;
        } catch (ValidatorException e) {
            logger.error("can't validate object", e);
            throw new ActionException("problem with validator", e);
        }
    }
}
