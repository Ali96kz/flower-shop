package com.epam.az.flower.shop.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AddProductValidator extends AbstractValidator {
    private static final String FIELD_MENU_FLOWER_NAME = "flower name";
    private static final String FIELD_MENU_DESCRIPTION = "description";
    private static final String FIELD_MENU_AVERAGE_HEIGHT= "average height";
    private static final String FIELD_MENU_PRICE = "price";

    private static final String PARAMETER_NAME_PRICE = "price";
    private static final String PARAMETER_NAME_DESCRIPTION = "description";
    private static final String PARAMETER_NAME_FLOWER_NAME = "flowerName";
    private static final String PARAMETER_NAME_AVERAGE_HEIGHT = "averageHeight";

    @Override
    public List<String> isValidate(HttpServletRequest request) {
        List<String> errorMsg = new ArrayList<>();

        validateString(errorMsg, request.getParameter(PARAMETER_NAME_FLOWER_NAME), FIELD_MENU_FLOWER_NAME, 4, 16);
        validateString(errorMsg, request.getParameter(PARAMETER_NAME_DESCRIPTION), FIELD_MENU_DESCRIPTION, 16, 144);

        validatePositiveNumber(errorMsg, request.getParameter(PARAMETER_NAME_AVERAGE_HEIGHT), FIELD_MENU_AVERAGE_HEIGHT);
        validatePositiveNumber(errorMsg, request.getParameter(PARAMETER_NAME_PRICE), FIELD_MENU_PRICE);

        return errorMsg;
    }
}
