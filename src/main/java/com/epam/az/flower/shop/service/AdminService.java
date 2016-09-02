package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.OrderDAO;
import com.epam.az.flower.shop.dao.ProductDAO;
import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.entity.UserOrder;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.User;

import java.util.List;

public class AdminService {
    public List<UserOrder> getAllOrders(){
        OrderDAO orderDAO = new OrderDAO();
        List<UserOrder> userOrders = orderDAO.getAll();
        return userOrders;
    }

    public List<User> getAllUsers() {
        UserDAO userDAO = new UserDAO();
        return userDAO.getAll();
    }
    public List<Product> getAllProducts() {
        ProductDAO productDAO = new ProductDAO();
        return productDAO.getAll();
    }

}
