package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.PaginatedList;
import com.epam.az.flower.shop.util.StringAdapter;
import com.epam.az.flower.shop.service.ProductService;
import com.epam.az.flower.shop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractVitrine implements Action {
    private final int PAGE_SIZE = 12;
    private List<Integer> pageNumbers ;
    private ProductService productService;

    public AbstractVitrine() throws ActionException {
        try {
            productService = new ProductService();
        } catch (ServiceException e) {
            throw new ActionException("can't initialize class", e);
        }
    }
    StringAdapter stringAdapter = new StringAdapter();
    PaginatedList paginatedList ;

    public void setPaginationList(HttpServletRequest req) throws ActionException {
        if(paginatedList == null){
            try {
                paginatedList = new PaginatedList(PAGE_SIZE, productService.getAllProduct());
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
    public void setPage(HttpServletRequest req){
        if (pageNumbers == null){
            pageNumbers = new ArrayList<>();
            for (int i = 0; i < paginatedList.getPageNumber(); i++) {
                pageNumbers.add(i+1);
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
