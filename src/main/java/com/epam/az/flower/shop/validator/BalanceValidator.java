package com.epam.az.flower.shop.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class BalanceValidator extends AbstractValidator{
    private static final String PARAMETER_MONEY = "money";
    private static final String FIELD_MENU_NAME_MONEY = "money";

    public BalanceValidator() throws ValidatorException {
    }

    @Override
    public List<String> isValidate(HttpServletRequest request) {
        List<String> errorMsg = new ArrayList<>();
        validatePositiveNumber(errorMsg, request.getParameter(PARAMETER_MONEY), FIELD_MENU_NAME_MONEY);
        return errorMsg;
    }
}
