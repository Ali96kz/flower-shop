package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BuyBasketValidator implements Validator {
    private static Logger logger = LoggerFactory.getLogger(BuyBasketValidator.class);
    private static String HAVENT_ENOUGH_MONEY_ERROR = "error.havent.enough.money";
    private UserService userService = new UserService();


    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        List<String> errorMsg = new ArrayList<>();
        HttpSession session = request.getSession();

        if (session.getAttribute(SESSION_PARAMETER_USER_ID) == null) {
            errorMsg.add(SIGN_IN_ERROR);
            return errorMsg;
        }

        if (session.getAttribute(SESSION_PARAMETER_BASKET_OBJECT) == null) {
            errorMsg.add(ADD_PRODUCT_INTO_BASKET_ERROR_MSG);
            return errorMsg;
        }

        Basket basket = (Basket) session.getAttribute(SESSION_PARAMETER_BASKET_OBJECT);
        if (basket == null || basket.getProducts().size() == 0) {
            errorMsg.add(EMPTY_BASKET_ERROR);
            return errorMsg;
        }

        int userId = (int) session.getAttribute(SESSION_PARAMETER_USER_ID);

        User user;
        try {
            user = userService.findById(userId);
        } catch (ServiceException e) {
            logger.error("can't get user by id", e);
            throw new ValidatorException("can;t get user by id from dao", e);
        }

        if (user.getBalance() < basket.getSum()) {
            errorMsg.add(HAVENT_ENOUGH_MONEY_ERROR);
        }

        return errorMsg;
    }
}
