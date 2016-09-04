package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.ProductList;
import com.epam.az.flower.shop.entity.ProductPagination;
import com.epam.az.flower.shop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowVitrineAction implements Action {
    ProductService productService = new ProductService();
    ProductPagination productPagination = productService.getPagination();
    List<Integer> pageNumber;
    StringAdapter stringAdapter = new StringAdapter();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        if (pageNumber == null) {
            pageNumber = new ArrayList<>();
            for (int i = 0; i < productPagination.listSize(); i++) {
                pageNumber.add(i);
            }
        }

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

        return new ActionResult("vitrine");
    }
}
