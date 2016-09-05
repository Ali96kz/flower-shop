package com.epam.az.flower.shop.action.product;

import com.epam.az.flower.shop.action.Action;
import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProductEditAction implements Action {
    ProductService productService = new ProductService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        StringAdapter stringAdapter = new StringAdapter();
        ShowAddProductPageAction showAddProductPageAction = new ShowAddProductPageAction();
        showAddProductPageAction.execute(req, resp);

        int id = stringAdapter.toInt(req.getParameter("id"));
        Product product = productService.findById(id);
        req.setAttribute("product", product);
        return new ActionResult("product-add");
    }
}
