package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.Basket;
import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.validator.DeleteFromBasketValidator;
import com.epam.az.flower.shop.validator.Validator;
import com.epam.az.flower.shop.validator.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeleteProductFromBasket implements Action {
    private Validator validator = new DeleteFromBasketValidator();
    private StringAdapter stringAdapter = new StringAdapter();
    private static Logger logger = LoggerFactory.getLogger(DeleteProductFromBasket.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        if(!isValidate(req)) {
            return new ActionResult(JSP_PAGE_NAME_BASKET, true);
        }

        HttpSession session = req.getSession();
        Basket basket = (Basket) session.getAttribute(ATTRIBUTE_BASKET);
        int productId = stringAdapter.toInt(req.getParameter(PARAMETER_PRODUCT_ID));
        basket.delete(productId);
        return new ActionResult(JSP_PAGE_NAME_BASKET, true);
    }

    public boolean isValidate(HttpServletRequest request) throws ActionException {
        try {
            List<String> errorMsg = validator.isValidate(request);
            if (errorMsg.size() > 0) {
                request.setAttribute(ATTRIBUTE_ERROR_MSG, errorMsg);
                return false;
            }
            return true;
        } catch (ValidatorException e) {
            logger.error("can't isValidate", e);
            throw new ActionException("can't isValidate product id", e);
        }
    }
}

