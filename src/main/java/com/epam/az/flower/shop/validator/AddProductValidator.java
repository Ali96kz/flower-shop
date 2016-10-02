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
    public static final int DESCRIPTION_MIN_LENGTH = 16;
    public static final int DESCRIPTION_MAX_LENGTH = 144;
    public static final int FLOWER_NAME_MIN_LENGTH = 4;
    public static final int FLOWER_NAME_MAX_LENGTH = 16;

    @Override
    public List<String> isValidate(HttpServletRequest request) {
        List<String> errorMsg = new ArrayList<>();

        validateString(errorMsg, request.getParameter(PARAMETER_NAME_FLOWER_NAME), FIELD_MENU_FLOWER_NAME, FLOWER_NAME_MIN_LENGTH, FLOWER_NAME_MAX_LENGTH);
        validateString(errorMsg, request.getParameter(PARAMETER_NAME_DESCRIPTION), FIELD_MENU_DESCRIPTION, DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH);

        validatePositiveNumber(errorMsg, request.getParameter(PARAMETER_NAME_AVERAGE_HEIGHT), FIELD_MENU_AVERAGE_HEIGHT);
        validatePositiveNumber(errorMsg, request.getParameter(PARAMETER_NAME_PRICE), FIELD_MENU_PRICE);

        return errorMsg;
    }
}
