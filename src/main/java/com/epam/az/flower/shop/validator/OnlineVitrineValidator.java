package com.epam.az.flower.shop.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class OnlineVitrineValidator extends AbstractValidator {
    public static final String PARAMETER_NAME_PAGE = "page";

    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        String pageNumber = request.getParameter(PARAMETER_NAME_PAGE);
        List<String> errorMsg = new ArrayList<>();
        validatePositiveNumber(errorMsg, pageNumber, "page");
        return errorMsg;
    }
}
