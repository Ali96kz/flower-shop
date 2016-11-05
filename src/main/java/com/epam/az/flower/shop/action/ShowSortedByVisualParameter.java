package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.PaginatedList;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowSortedByVisualParameter extends AbstractVitrine {
    private ProductService productService = new ProductService();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            int visualParameterId = Integer.parseInt(req.getParameter(PARAMETER_NAME_VISUAL_PARAMETERS_ID));
            PaginatedList paginatedList = productService.getAllByVisualParameter(visualParameterId);
            List<Product> products = paginatedList.getPage(0);
            req.setAttribute(ATTRIBUTE_PRODUCTS, products);
            return new ActionResult(JSP_PAGE_NAME_VITRINE);
        } catch (ServiceException e) {
            throw new ActionException("", e);
        }
    }

}
