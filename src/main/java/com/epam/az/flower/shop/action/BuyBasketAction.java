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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BuyBasketAction implements Action{
    public static final String JSP_PAGE_NAME_BILL = "bill";
    UserService userService;
    OrderService orderService;

    public BuyBasketAction() throws ActionException {
        try {
            orderService = new OrderService();
            userService = new UserService();

        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }


    Validator validator = new BuyBasketValidator();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        List<String> errorMsg ;
        try {
            errorMsg = validator.isValidate(req);
        } catch (ValidatorException e) {
            throw new ActionException("problem with validation", e);
        }
        if (errorMsg.size() >  0){
            req.setAttribute("errorMsg", errorMsg);
            return new ActionResult("basket", true);
        }

        Basket basket = (Basket) session.getAttribute("basket");
        int userId = (int) session.getAttribute("userId");
        User user;
        try {
            user = userService.findById(userId);
        } catch (ServiceException e) {
            throw new ActionException("can't get user from service", e);
        }

        int sum = 0;
        for (Product product : basket.getProducts()) {
            sum += product.getPrice();
            try {
                orderService.createOrder(user, product);
            } catch (ServiceException e) {
                throw new ActionException("can't create order", e);
            }
        }

        session.setAttribute("basket", null);
        req.setAttribute("summ", sum);
        return new ActionResult(JSP_PAGE_NAME_BILL);
    }
}
