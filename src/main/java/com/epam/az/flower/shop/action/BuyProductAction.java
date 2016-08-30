package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.OrderService;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyProductAction implements Action {
    StringAdapter stringAdapter = new StringAdapter();
    OrderService orderService = new OrderService();
    UserService userService = new UserService();
    ProductService productService = new ProductService();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int userId = (int) req.getSession().getAttribute("userId");
        int productId = stringAdapter.toInt(req.getParameter("productId"));
        Product product = productService.findById(productId);
        User user = userService.getUserByID(userId);
        orderService.createOrder(user, product);

        req.setAttribute("price", product.getPrice());
        return new ActionResult("bill");
    }
}
