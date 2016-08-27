package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.DAO.OrderDAO;
import com.epam.az.flower.shop.DAO.ProductDAO;
import com.epam.az.flower.shop.DAO.UserDAO;
import com.epam.az.flower.shop.entity.Order;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.User;

import java.util.List;

public class AdminService {
    public List<Order> getAllOrders(){
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.getAll();
        return orders;
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
