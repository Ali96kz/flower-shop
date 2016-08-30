package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.DAO.OrderDAO;
import com.epam.az.flower.shop.DAO.UserDAO;
import com.epam.az.flower.shop.entity.Order;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.User;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private UserDAO userDAO = new UserDAO();
    public void createOrder(User user, Product product){
        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        orderDAO.insert(order);

        user.setBalance(user.getBalance()-product.getPrice());
        userDAO.update(user);
    }

}
