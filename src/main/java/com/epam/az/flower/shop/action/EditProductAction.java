package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Flower;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.validator.AddProductValidator;
import com.epam.az.flower.shop.validator.Validator;
import com.epam.az.flower.shop.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EditProductAction extends AbstractProduct {

    public static final String ATTRIBUTE_NAME_ERROR_MSG = "errorMsg";
    public static final String JSP_PAGE_NAME_EDIT_PRODUCT = "product-edit";
    public static final String JSP_PAGE_NAME_PRODUCT = "product-inf";
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "?productId=";
    public static final String PARAMETER_PRODUCT_ID = "productId";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            ActionResult actionResult = validate(req);
            if (actionResult != null) {
                setValue(req);
                setProduct(req);
                return actionResult;
            }

            int productId = stringAdapter.toInt(req.getParameter(PARAMETER_PRODUCT_ID));
            Flower flower = productService.findById(productId).getFlower();
            Product product = getProduct(req, new Product());
            product.getFlower().setId(flower.getId());
            product.setId(productId);
            productService.update(product);
            return new ActionResult(JSP_PAGE_NAME_PRODUCT + ATTRIBUTE_NAME_PRODUCT_ID + productId, true);
        } catch (ServiceException e) {
            throw new ActionException("can't get user by id", e);
        }
    }

    public ActionResult validate(HttpServletRequest req) throws ActionException {
        Validator validator = new AddProductValidator();
        List<String> errorMsg;
        try {
            errorMsg = validator.isValidate(req);
            if (errorMsg.size() > 0) {
                req.setAttribute(ATTRIBUTE_NAME_ERROR_MSG, errorMsg);

                return new ActionResult(JSP_PAGE_NAME_EDIT_PRODUCT);
            }
        } catch (ValidatorException e) {
            throw new ActionException("problem with validator", e);
        }
        return null;
    }
}
