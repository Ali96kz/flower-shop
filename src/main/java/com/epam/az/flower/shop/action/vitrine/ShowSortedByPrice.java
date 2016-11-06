package com.epam.az.flower.shop.action.vitrine;

import com.epam.az.flower.shop.action.AbstractVitrine;
import com.epam.az.flower.shop.action.ActionException;
import com.epam.az.flower.shop.action.ActionResult;
import com.epam.az.flower.shop.entity.PaginatedList;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowSortedByPrice extends AbstractVitrine{

    private static final String PRICE_MIN_PARAMETER = "priceMin";
    private static final String PRICE_MAX_PARAMETER = "priceMax";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            int min = Integer.parseInt(req.getParameter(PRICE_MIN_PARAMETER));
            int max = Integer.parseInt(req.getParameter(PRICE_MAX_PARAMETER));
            PaginatedList paginatedList = productService.getAllByPrice(min, max);
            List<Product> products = paginatedList.getPage(0);
            req.setAttribute(ATTRIBUTE_PRODUCTS, products);

            return new ActionResult(JSP_PAGE_NAME_VITRINE);
        } catch (ServiceException e) {
            throw new ActionException("", e);
        }
    }
}
