package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BuyBasketValidator implements Validator {
    UserService userService = new UserService();
    StringAdapter stringAdapter = new StringAdapter();
    ProductService productService = new ProductService();


    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
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
        Basket basket = (Basket) session.getAttribute("basket");
        int summ = 0;
        int userId = (int) session.getAttribute("userId");
        User user = null;
        try {
            user = userService.findById(userId);
        } catch (ServiceException e) {
            throw new ValidatorException("can;t get user by id from dao", e);
        }

        for (Product product : basket.getProducts()) {
            summ += product.getPrice();
        }

        if(user.getBalance() < summ){
            errorMsg.add("you haven't enough");
        }

        return errorMsg;
    }
}
