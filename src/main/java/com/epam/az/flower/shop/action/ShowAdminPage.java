package com.epam.az.flower.shop.action;

import com.epam.az.flower.shop.entity.ActionResult;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAdminPage implements Action {
    AdminService adminService = new AdminService();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<User> users = adminService.getAllUsers();
        List<Product> products = adminService.getAllProducts();

        req.setAttribute("users", users);
        req.setAttribute("products", products);

        return new ActionResult("admin");
    }
}
