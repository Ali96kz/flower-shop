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

public class ShowOnlineVitrineAction extends AbstractVitrine{
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Integer> pageNumber = addPageNumber();
        setPaginationList(req, pageNumber);
        return new ActionResult("vitrine");
    }
}
