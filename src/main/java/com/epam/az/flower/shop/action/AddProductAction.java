package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
}
