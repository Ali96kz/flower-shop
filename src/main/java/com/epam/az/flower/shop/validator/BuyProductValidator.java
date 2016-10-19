package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.util.StringAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BuyProductValidator extends AbstractValidator {
    public static final String PARAMETER_USER_ID = "userId";
    public static final String PARAMETER_PRODUCT_ID = "productId";
    private UserService userService = new UserService();
    private StringAdapter stringAdapter = new StringAdapter();
    private ProductService productService = new ProductService();

    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        List<String> errorMsg = new ArrayList<>();
        HttpSession session = request.getSession();

        if (session.getAttribute(PARAMETER_USER_ID) == null) {
            errorMsg.add("You must sign in, to buy something in own shop");
            return errorMsg;
        }
        try {
            validatePositiveNumber(errorMsg, request.getParameter(PARAMETER_PRODUCT_ID), "");
            if (errorMsg.size() != 0) {
                return errorMsg;
            }

            int userId = (int) session.getAttribute(PARAMETER_USER_ID);
            int productId = stringAdapter.toInt(request.getParameter(PARAMETER_PRODUCT_ID));


            if (!productService.isExist(productId)) {
                errorMsg.add("This product don't exist in our vitrine");
                return errorMsg;
            }

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
