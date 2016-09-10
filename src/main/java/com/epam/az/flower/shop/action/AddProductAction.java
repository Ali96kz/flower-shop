package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.*;
import com.epam.az.flower.shop.validator.AddProductValidator;
import com.epam.az.flower.shop.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddProductAction extends AbstractProduct {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        ActionResult actionResult = validate(req, resp);
        if (actionResult != null) {
            return actionResult;
        }

        Product product = getProduct(req, new Product());
        int productId = productService.addNewProduct(product);
        return new ActionResult("product-inf?id=" + productId, true);
    }

    public ActionResult validate(HttpServletRequest req, HttpServletResponse resp) {
        Validator validator = new AddProductValidator();
        List<String> errorMsg = validator.isValidate(req);
        if (errorMsg.size() > 0) {
            req.setAttribute("errorMsg", errorMsg);
            return new ActionResult("product-add");
        }
        return null;
    }
}
