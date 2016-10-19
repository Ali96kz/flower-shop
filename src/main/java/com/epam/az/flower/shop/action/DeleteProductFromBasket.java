package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.validator.DeleteFromBasketValidator;
import com.epam.az.flower.shop.validator.Validator;
import com.epam.az.flower.shop.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteProductFromBasket implements Action {
    private static final String JSP_PAGE_NAME_BASKET = "basket";
    private static final String ATTRIBUTE_BASKET = "basket";
    private static final String PARAMETER_PRODUCT_ID = "id";
    private Validator validator = new DeleteFromBasketValidator();
    private StringAdapter stringAdapter = new StringAdapter();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            if (validator.isValidate(req).size() > 0) {
                return new ActionResult(JSP_PAGE_NAME_BASKET, true);
            }

        } catch (ValidatorException e) {
            throw new ActionException("can't validate product id", e);
        }
        HttpSession session = req.getSession();
        Basket basket = (Basket) session.getAttribute(ATTRIBUTE_BASKET);
        int productId = stringAdapter.toInt(req.getParameter(PARAMETER_PRODUCT_ID));
        basket.delete(productId);
        return new ActionResult(JSP_PAGE_NAME_BASKET, true);
    }
}

