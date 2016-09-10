package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.*;
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
        ActionResult actionResult = validate(req, resp);
        if (actionResult != null) {
            return actionResult;
        }

        Product product = getProduct(req, new Product());
        int productId = 0;
        try {
            productId = productService.addNewProduct(product);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult("product-inf?id=" + productId, true);
    }

    public ActionResult validate(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        Validator validator = new AddProductValidator();
        List<String> errorMsg = null;
        try {
            errorMsg = validator.isValidate(req);
        } catch (ValidatorException e) {
            throw new ActionException("problem with product validator", e);
        }
        if (errorMsg.size() > 0) {
            req.setAttribute("errorMsg", errorMsg);
            return new ActionResult("product-add");
        }
        return null;
    }
}
