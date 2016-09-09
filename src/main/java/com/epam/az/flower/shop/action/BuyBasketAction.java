package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.OrderService;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.validator.BuyBasketValidator;
import com.epam.az.flower.shop.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BuyBasketAction implements Action{
    UserService userService = new UserService();
    OrderService orderService = new OrderService();
    Validator validator = new BuyBasketValidator();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        List<String> errorMsg = validator.isValidate(req);
        if (errorMsg.size() >  0){
            req.setAttribute("errorMsg", errorMsg);
            return new ActionResult("basket", true);
        }

        Basket basket = (Basket) session.getAttribute("basket");
        int summ = 0;
        int userId = (int) session.getAttribute("userId");
        User user = userService.findByID(userId);

        for (Product product : basket.getProducts()) {
            summ += product.getPrice();
            orderService.createOrder(user, product);
        }
        session.setAttribute("basket", null);
        req.setAttribute("summ", summ);
        return new ActionResult("bill");
    }
}
