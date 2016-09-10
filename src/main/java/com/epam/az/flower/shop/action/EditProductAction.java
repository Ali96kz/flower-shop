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

public class EditProductAction extends AbstractProduct{

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        int productId = stringAdapter.toInt(req.getParameter("id"));
        Flower flower = null;
        try {
            flower = productService.findById(productId).getFlower();
        } catch (ServiceException e) {
            throw new ActionException("can't get user by id", e);
        }

        Product product = getProduct(req, new Product());
        product.getFlower().setId(flower.getId());
        product.setId(productId);
        productService.update(product);
        return new ActionResult("product-inf?id=" + productId, true);
    }

    public ActionResult validate(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        Validator validator = new AddProductValidator();
        List<String> errorMsg = null;
        try {
            errorMsg = validator.isValidate(req);
        } catch (ValidatorException e) {
            throw new ActionException("problem with validator", e);
        }
        if (errorMsg.size() > 0) {
            req.setAttribute("errorMsg", errorMsg);
            return new ActionResult("edit-product");
        }
        return null;
    }
}
