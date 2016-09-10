package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.OrderService;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.validator.BuyProductValidator;
import com.epam.az.flower.shop.validator.Validator;
import com.epam.az.flower.shop.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BuyProductAction implements Action {
    StringAdapter stringAdapter = new StringAdapter();
    OrderService orderService = new OrderService();
    UserService userService = new UserService();
    ProductService productService = new ProductService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        Validator validator = new BuyProductValidator();
        List<String> errorMsg = null;
        try {
            errorMsg = validator.isValidate(req);
        } catch (ValidatorException e) {
            throw new ActionException("problem with validating", e);
        }
        int productId = stringAdapter.toInt(req.getParameter("productId"));

        if (errorMsg.size() > 0) {
            req.setAttribute("errorMsg", errorMsg);
            return new ActionResult("product-inf?productId =" + productId, true);
        }

        int userId = (int) req.getSession().getAttribute("userId");
        Product product = null;
        User user = null;
        try {
            product = productService.findById(productId);
            user = userService.findById(userId);
        } catch (ServiceException e) {
            throw new ActionException("can't get entity by id from dao", e);
        }

        orderService.createOrder(user, product);

        req.setAttribute("price", product.getPrice());
        return new ActionResult("bill");
    }
}
