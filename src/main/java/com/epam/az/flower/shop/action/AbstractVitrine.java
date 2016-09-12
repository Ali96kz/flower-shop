package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.dao.AbstractDAO;
import com.epam.az.flower.shop.entity.PaginatedList;
import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractVitrine implements Action {
    ProductService productService;

    public AbstractVitrine() throws ActionException {
        try {
            productService = new ProductService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize class", e);
        }
    }

    StringAdapter stringAdapter = new StringAdapter();
    PaginatedList paginatedList ;
    private final int pageSize = 10;
    public void setPaginationList(HttpServletRequest req) throws ActionException {
        if(paginatedList == null){
            try {
                paginatedList = new PaginatedList(pageSize, productService.getAllProduct());
            } catch (ServiceException e) {
                throw new ActionException("can't get product from service",e);
            }
        }
        int pageNumber;
        if (req.getParameter("page") == null) {
            pageNumber = 0;
        } else {
            String page = req.getParameter("page");
            pageNumber = stringAdapter.toInt(page);

        }
        req.setAttribute("products",paginatedList.getPage(pageNumber));
    }
}
