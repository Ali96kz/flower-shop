package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.util.StringAdapter;
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
    public static final String PARAMETER_USER_ID = "userId";
    public static final String PARAMETER_PRODUCT_ID = "productId";
    UserService userService;
    StringAdapter stringAdapter = new StringAdapter();
    ProductService productService ;

    public BuyProductValidator() throws ActionException {
        try {
            userService = new UserService();
            productService = new ProductService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize service class", e);
        }
    }

    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        List<String> errorMsg = new ArrayList<>();
        HttpSession session = request.getSession();

        if (session.getAttribute(PARAMETER_USER_ID) == null) {
            errorMsg.add("You must sign in, to buy something in own shop");
            return errorMsg;
        }

        int userId = (int) session.getAttribute(PARAMETER_USER_ID);
        int productId = stringAdapter.toInt(request.getParameter(PARAMETER_PRODUCT_ID));

        try {
            User user = userService.findById(userId);
            Product product = productService.findById(productId);

            if (user.getBalance() < product.getPrice()) {
                errorMsg.add("You haven't enough money");
            }

        } catch (ServiceException e) {
            throw new ValidatorException("can't get entity from service", e);
        }

        return errorMsg;
    }
}
