package com.epam.az.flower.shop.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class OnlineVitrineValidator extends AbstractValidator {
    private static final String ERROR_INCORRECT_PAGE_NUMBER = "error.online.vitrine.incorrect.number";

    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        String pageNumber = request.getParameter(PARAMETER_NAME_PAGE);
        List<String> errorMsg = new ArrayList<>();
        validatePositiveNumber(errorMsg, pageNumber, ERROR_INCORRECT_PAGE_NUMBER);
        return errorMsg;
    }
}
