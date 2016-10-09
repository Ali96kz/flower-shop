package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.action.ActionException;
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
    public static final String SESSION_PARAMETER_USER_ID = "userId";
    public static final String SESSION_PARAMTER_BASKET_OBJECT = "basket";
    UserService userService;
    ProductService productService;

    public BuyBasketValidator() throws ActionException {
        try {
            productService = new ProductService();
            userService = new UserService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }


    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        List<String> errorMsg = new ArrayList<>();
        HttpSession session = request.getSession();

        if (session.getAttribute(SESSION_PARAMETER_USER_ID) == null) {
            errorMsg.add("You must sign in to buy something in own shop");
            return errorMsg;
        }

        if (session.getAttribute(SESSION_PARAMTER_BASKET_OBJECT) == null) {
            errorMsg.add("You must add some products in your basket");
            return errorMsg;
        }

        Basket basket = (Basket) session.getAttribute(SESSION_PARAMTER_BASKET_OBJECT);
        if(basket == null || basket.getProducts().size() == 0){
            errorMsg.add("Basket is empty");
            return errorMsg;
        }
        int summ = 0;
        int userId = (int) session.getAttribute(SESSION_PARAMETER_USER_ID);

        User user;
        try {
            user = userService.findById(userId);
        } catch (ServiceException e) {
            throw new ValidatorException("can;t get user by id from dao", e);
        }

        for (Product product : basket.getProducts()) {
            summ += product.getPrice();
        }

        if (user.getBalance() < summ) {
            errorMsg.add("you haven't enough");
        }

        return errorMsg;
    }
}
