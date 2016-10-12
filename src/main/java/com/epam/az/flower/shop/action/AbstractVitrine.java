package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.PaginatedList;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;
import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.validator.OnlineVitrineValidator;
import com.epam.az.flower.shop.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractVitrine implements Action {
    public static final String ATTRIBUTE_NAME_PRODUCTS = "products";
    public static final String PARAMETER_NAME_PAGE = "page";
    private final int PAGE_SIZE = 12;
    private List<Integer> pageNumbers;
    private ProductService productService;
    private OnlineVitrineValidator onlineVitrineValidator = new OnlineVitrineValidator();

    private StringAdapter stringAdapter = new StringAdapter();
    private PaginatedList paginatedList;

    public AbstractVitrine() throws ActionException {
        try {
            productService = new ProductService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize class", e);
        }
    }

    public void setPaginationList(HttpServletRequest req) throws ActionException {
        int pageNumber;

        try {
            paginatedList = new PaginatedList(PAGE_SIZE, productService.getAllNotDeleteProduct());
        } catch (ServiceException e) {
            throw new ActionException("can't get product from service", e);
        }

        try {
            List<String> erorrMsg = onlineVitrineValidator.isValidate(req);

            if (erorrMsg.size() > 0) {
                pageNumber = 0;
                req.setAttribute(ATTRIBUTE_NAME_PRODUCTS, paginatedList.getPage(pageNumber));
                return;
            }

            String page = req.getParameter(PARAMETER_NAME_PAGE);
            pageNumber = stringAdapter.toInt(page);


            if (pageNumber > paginatedList.getPageNumber()) {
                pageNumber = 0;
            }

            req.setAttribute("products", paginatedList.getPage(pageNumber));
        } catch (ValidatorException e) {
            throw new ActionException("can't validate", e);
        }
    }

    public void setPage(HttpServletRequest req) {
        if (pageNumbers == null) {
            pageNumbers = new ArrayList<>();
            for (int i = 0; i < paginatedList.getPageNumber(); i++) {
                pageNumbers.add(i + 1);
            }
        } else if (pageNumbers.size() != paginatedList.getPageNumber()) {
            pageNumbers = new ArrayList<>();
            for (int i = 0; i < paginatedList.getPageNumber(); i++) {
                pageNumbers.add(i + 1);
            }
        }
        req.setAttribute("pageList", pageNumbers);
    }


}
