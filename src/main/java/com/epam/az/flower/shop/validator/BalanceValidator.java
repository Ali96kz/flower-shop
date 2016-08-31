package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.adapter.StringAdapter;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class BalanceValidator implements Validator{
    StringAdapter stringAdapter = new StringAdapter();
    @Override
    public List<String> isValidate(HttpServletRequest request) {
        List<String> errorMsg = new ArrayList<>();
        Integer money = stringAdapter.toInt(request.getParameter("money"));
        if (request.getParameter("money") == null) {
            errorMsg.add("you didn't insert a money value");
            return errorMsg;
        }
        if (money <  0){
            errorMsg.add("insert moneu couldn't < 0");
        }
        return errorMsg;
    }
}
