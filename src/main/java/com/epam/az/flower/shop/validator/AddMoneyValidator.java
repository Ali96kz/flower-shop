package com.epam.az.flower.shop.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AddMoneyValidator extends AbstractValidator {

    @Override
    public List<String> isValidate(HttpServletRequest request) {
        List<String> errorMsg = new ArrayList<>();
        validatePositiveNumber(errorMsg, request.getParameter(PARAMETER_MONEY), ERROR_MSG_CASH_INCORRECT_MONEY);
        return errorMsg;
    }
}
