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

    public EditProductAction() throws ActionException {
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            int productId = stringAdapter.toInt(req.getParameter("id"));
            Flower flower = productService.findById(productId).getFlower();
            Product product = getProduct(req, new Product());
            product.getFlower().setId(flower.getId());
            product.setId(productId);
            productService.update(product);
            return new ActionResult("product-inf?id=" + productId, true);
        } catch (ServiceException e) {
            throw new ActionException("can't get user by id", e);
        }

    }

    public ActionResult validate(HttpServletRequest req) throws ActionException {
        Validator validator = new AddProductValidator();
        List<String> errorMsg = null;
        try {
            errorMsg = validator.isValidate(req);
            if (errorMsg.size() > 0) {
                req.setAttribute("errorMsg", errorMsg);
                return new ActionResult("edit-product");
            }
        } catch (ValidatorException e) {
            throw new ActionException("problem with validator", e);
        }
        return null;
    }
}
