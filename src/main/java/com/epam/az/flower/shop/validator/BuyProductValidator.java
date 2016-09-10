package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BuyProductValidator implements Validator{
    UserService userService = new UserService();
    StringAdapter stringAdapter = new StringAdapter();
    ProductService productService = new ProductService();
    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        List<String> errorMsg = new ArrayList<>();
        HttpSession session = request.getSession();

        if (session.getAttribute("userId") == null) {
            errorMsg.add("You must sign in, to buy something in own shop");
            return errorMsg;
        }

        int userId = (int) session.getAttribute("userId");
        int productId = stringAdapter.toInt(request.getParameter("productId"));
        User user = null;
        Product product = null;

        try {
            user = userService.findById(userId);
            product = productService.findById(productId);
        } catch (ServiceException e) {
            throw new ValidatorException("can't get entity from service", e);
        }

        if (user.getBalance() < product.getPrice()) {
            errorMsg.add("You haven't enough money");
        }

        return errorMsg;
    }
}
