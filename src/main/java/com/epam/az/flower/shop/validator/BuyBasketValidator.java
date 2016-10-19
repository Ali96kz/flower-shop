package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BuyBasketValidator implements Validator {
    private static final String SESSION_PARAMETER_USER_ID = "userId";
    private static final String SESSION_PARAMETER_BASKET_OBJECT = "basket";
    private UserService userService = new UserService();


    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        List<String> errorMsg = new ArrayList<>();
        HttpSession session = request.getSession();

        if (session.getAttribute(SESSION_PARAMETER_USER_ID) == null) {
            errorMsg.add("You must sign in to buy something in own shop");
            return errorMsg;
        }

        if (session.getAttribute(SESSION_PARAMETER_BASKET_OBJECT) == null) {
            errorMsg.add("You must add some products in your basket");
            return errorMsg;
        }

        Basket basket = (Basket) session.getAttribute(SESSION_PARAMETER_BASKET_OBJECT);
        if (basket == null || basket.getProducts().size() == 0) {
            errorMsg.add("Basket is empty");
            return errorMsg;
        }
        int userId = (int) session.getAttribute(SESSION_PARAMETER_USER_ID);

        User user;
        try {
            user = userService.findById(userId);
        } catch (ServiceException e) {
            throw new ValidatorException("can;t get user by id from dao", e);
        }

        if (user.getBalance() < basket.getSum()) {
            errorMsg.add("you haven't enough");
        }

        return errorMsg;
    }
}
