package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.OrderService;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.service.UserService;
import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.validator.BuyProductValidator;
import com.epam.az.flower.shop.validator.Validator;
import com.epam.az.flower.shop.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BuyProductAction implements Action {

    private StringAdapter stringAdapter = new StringAdapter();
    private ProductService productService = new ProductService();
    private Validator validator = new BuyProductValidator();
    private UserService userService = new UserService();
    private OrderService orderService = new OrderService();


    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        List<String> errorMsg;

        try {
            errorMsg = validator.isValidate(req);
        } catch (ValidatorException e) {
            throw new ActionException("problem with validating", e);
        }


        if (errorMsg.size() > 0) {
            req.setAttribute(ATTRIBUTE_NAME_ERROR_MSG, errorMsg);
            return new ActionResult(JSP_PAGE_NAME_VITRINE, true);
        }

        int productId = stringAdapter.toInt(req.getParameter(PARAMETER_PRODUCT_ID));

        int userId = (int) req.getSession().getAttribute(ATTRIBUTE_USER_ID);
        Product product;
        User user;
        try {
            product = productService.findById(productId);
            user = userService.findById(userId);
        } catch (ServiceException e) {
            throw new ActionException("can't get entity by id from dao", e);
        }

        try {
            orderService.createOrder(user, product);
        } catch (ServiceException e) {
            throw new ActionException("can't create order", e);
        }

        req.setAttribute(ATTRIBUTE_NAME_PRICE, product.getPrice());
        return new ActionResult(JSP_PAGE_NAME_BILL);
    }
}
