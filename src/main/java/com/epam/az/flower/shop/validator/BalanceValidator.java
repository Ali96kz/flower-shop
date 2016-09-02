package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BalanceValidator implements Validator{
    StringAdapter stringAdapter = new StringAdapter();
    UserService userService = new UserService();
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
