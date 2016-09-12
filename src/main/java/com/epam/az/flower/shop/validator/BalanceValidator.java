package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class BalanceValidator implements Validator{
    StringAdapter stringAdapter = new StringAdapter();
    UserService userService;

    public BalanceValidator() throws ValidatorException {
        try {
            userService = new UserService();
        } catch (ServiceException e) {
            throw new ValidatorException("can't initialize service", e);
        }
    }

    @Override
    public List<String> isValidate(HttpServletRequest request) {
        List<String> errorMsg = new ArrayList<>();
        Integer money = stringAdapter.toInt(request.getParameter("money"));

        if (request.getParameter("money") == null) {
            errorMsg.add("you didn't insert a money value");
            return errorMsg;
        }

        if (money <  0){
            errorMsg.add("insert money couldn't < 0");
        }

        return errorMsg;
    }
}
