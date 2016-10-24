package com.epam.az.flower.shop.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class OnlineVitrineValidator extends AbstractValidator {

    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        String pageNumber = request.getParameter(PARAMETER_NAME_PAGE);
        List<String> errorMsg = new ArrayList<>();
        validatePositiveNumber(errorMsg, pageNumber, PARAMETER_NAME_PAGE);
        return errorMsg;
    }
}
