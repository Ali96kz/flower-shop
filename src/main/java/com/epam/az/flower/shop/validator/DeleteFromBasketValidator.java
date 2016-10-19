package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.util.StringAdapter;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class DeleteFromBasketValidator extends AbstractValidator {

    private static final String PRODUCT_ID = "id";
    private static final String ATTRIBUTE_NAME_BASKET = "basket";
    private static final String ERROR_PRODUCT_NAME = "product id";
    private StringAdapter stringAdapter = new StringAdapter();

    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        List<String> errorMsg = new ArrayList<>();
        String productId = request.getParameter(PRODUCT_ID);
        validatePositiveNumber(errorMsg, productId, ERROR_PRODUCT_NAME);
        if (errorMsg.size() > 0) {
            return errorMsg;
        }
        Basket basket = (Basket) request.getSession().getAttribute(ATTRIBUTE_NAME_BASKET);
        if ((basket.getProducts().size() - 1) < stringAdapter.toInt(productId)) {
            errorMsg.add("this product don't add in basket");
        }

        return errorMsg;
    }
}
