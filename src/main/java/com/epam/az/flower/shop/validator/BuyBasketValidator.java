package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BuyBasketValidator implements Validator {
    UserService userService = new UserService();
    StringAdapter stringAdapter = new StringAdapter();

    @Override
    public List<String> isValidate(HttpServletRequest request) {
        List<String> errorMsg = new ArrayList<>();
        HttpSession session = request.getSession();

        if (session.getAttribute("userId") == null) {
            errorMsg.add("You must sign in to buy something in own shop");
            return errorMsg;
        }

        if (session.getAttribute("basket") == null) {
            errorMsg.add("You must add some products in your basket");
            return errorMsg;
        }


        return errorMsg;
    }
}
