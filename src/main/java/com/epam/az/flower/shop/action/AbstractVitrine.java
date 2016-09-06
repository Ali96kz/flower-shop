package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.ProductList;
import com.epam.az.flower.shop.entity.ProductPagination;
import com.epam.az.flower.shop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public abstract  class AbstractVitrine implements Action {
    ProductService productService = new ProductService();
    ProductPagination productPagination = productService.getPagination();
    StringAdapter stringAdapter = new StringAdapter();
    List<Integer> pageNumber;
    public List<Integer> addPageNumber() {
        if (pageNumber == null) {
            pageNumber = new ArrayList<>();
            for (int i = 0; i < productPagination.listSize(); i++) {
                pageNumber.add(i);
            }
        }
        return pageNumber;
    }



    public void setPaginationList(HttpServletRequest req, List<Integer> pageNumber){
        int id;
        if (req.getParameter("page") == null) {
            id = 0;
        } else {
            String page = req.getParameter("page");
            id = stringAdapter.toInt(page);
        }
        ProductList productList = productPagination.getProductList(id);
        req.setAttribute("products", productList.products());
        req.setAttribute("pageList", pageNumber);

    }
}
