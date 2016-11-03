package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.OrderService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.validator.BuyBasketValidator;
import com.epam.az.flower.shop.validator.Validator;
import com.epam.az.flower.shop.validator.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BuyBasketAction extends AbstractBasket {
    private static Logger logger = LoggerFactory.getLogger(BuyBasketAction.class);
    private final ActionResult actionResult = new ActionResult(JSP_PAGE_NAME_BASKET);
    private UserService userService = new UserService();
    private Validator validator = new BuyBasketValidator();
    private OrderService orderService = new OrderService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        if (!isValidate(req)) {
            return actionResult;
        }

        Basket basket = getBasket(req.getSession());
        int userId = (int) session.getAttribute(SESSION_PARAMETER_USER_ID);
        User user = findUserById(userId);
        int sum = createOrder(user, basket);
        session.setAttribute(ATTRIBUTE_BASKET, null);
        req.setAttribute(ATTRIBUTE_NAME_SUM, sum);
        return actionResult;
    }

    public int createOrder(User user, Basket basket) throws ActionException {
        for (Product product : basket.getProducts()) {
            try {
                orderService.createOrder(user, product);
            } catch (ServiceException e) {
                logger.error("can't create order", e);
                throw new ActionException("can't create order", e);
            }
        }
        return basket.getSum();
    }

    public boolean isValidate(HttpServletRequest req) throws ActionException {
        try {
            List<String> errorMsg = validator.isValidate(req);
            if (errorMsg.size() > 0) {
                req.setAttribute(ATTRIBUTE_ERROR_MSG, errorMsg);
                return false;
            }
            return true;
        } catch (ValidatorException e) {
            logger.error("can't isValidate object", e);
            throw new ActionException("problem with validation", e);
        }
    }

    public User findUserById(int userId) throws ActionException {
        try {
            User user = userService.findById(userId);
            return user;
        } catch (ServiceException e) {
            logger.error("can't find user by id", e);
            throw new ActionException("can't get user from service", e);
        }

    }
}
