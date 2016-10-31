package com.epam.az.flower.shop.validator;

import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.util.StringAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BuyProductValidator extends AbstractValidator {
    public static final String PRODUCT_ID = "";
    private static Logger logger = LoggerFactory.getLogger(BuyProductValidator.class);

    private UserService userService = new UserService();
    private StringAdapter stringAdapter = new StringAdapter();
    private ProductService productService = new ProductService();

    @Override
    public List<String> isValidate(HttpServletRequest request) throws ValidatorException {
        List<String> errorMsg = new ArrayList<>();
        HttpSession session = request.getSession();

        if (session.getAttribute(PARAMETER_USER_ID) == null) {
            errorMsg.add(SIGN_IN_ERROR_MSG);
            return errorMsg;
        }

        try {
            validatePositiveNumber(errorMsg, request.getParameter(PARAMETER_PRODUCT_ID), PRODUCT_ID);
            if (errorMsg.size() > 0) {
                return errorMsg;
            }

            int userId = (int) session.getAttribute(PARAMETER_USER_ID);
            int productId = stringAdapter.toInt(request.getParameter(PARAMETER_PRODUCT_ID));

            if (!productService.isExist(productId)) {
                errorMsg.add(UNEXIST_PRODUCT_ERROR);
                return errorMsg;
            }


            User user = userService.findById(userId);
            Product product = productService.findById(productId);

            if (user.getBalance() < product.getPrice()) {
                errorMsg.add(HANVEN_T_ENOUGH_MONEY);
            }


        } catch (ServiceException e) {
            logger.error("can't get entity from service class", e);
            throw new ValidatorException("can't get entity from service", e);
        }

        return errorMsg;
    }
}
