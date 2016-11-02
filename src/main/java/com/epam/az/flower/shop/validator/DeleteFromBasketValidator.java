package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.util.StringAdapter;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class DeleteFromBasketValidator extends AbstractValidator {

    private static final String ERROR_BASKET_DELETE_UNEXIST_PRODUCT = "error.basket.delete.unexist.product";
    private StringAdapter stringAdapter = new StringAdapter();

    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        List<String> errorMsg = new ArrayList<>();
        String productId = request.getParameter(PARAMETER_PRODUCT_ID);
        validatePositiveNumber(errorMsg, productId, ERROR_INCORRECT_ID_PRODUCT);

        if (errorMsg.size() > 0) {
            return errorMsg;
        }

        Basket basket = (Basket) request.getSession().getAttribute(ATTRIBUTE_NAME_BASKET);

        if (!basket.isExist(stringAdapter.toInt(productId))) {
            errorMsg.add(ERROR_BASKET_DELETE_UNEXIST_PRODUCT);
        }

        return errorMsg;
    }
}
