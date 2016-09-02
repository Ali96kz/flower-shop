package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.*;
import com.epam.az.flower.shop.entity.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class OrderService {
    DAOFactory daoFactory = DAOFactory.getInstance();
    private OrderDAO orderDAO = daoFactory.getDao(OrderDAO.class);
    private UserDAO userDAO = daoFactory.getDao(UserDAO.class);
    private UserBalanceDAO userBalanceDAO = daoFactory.getDao(UserBalanceDAO.class);
    Transaction transaction = new Transaction();

    public void createOrder(User user, Product product){
        UserOrder userOrder = new UserOrder();
        userOrder.setUser(user);
        userOrder.setProduct(product);
        userOrder.setOrderDate(getDate());
        orderDAO.insert(userOrder);

        user.setBalance(user.getBalance()-product.getPrice());
        userDAO.update(user);

        transaction.setId(3);
        UserBalance userBalance = new UserBalance();
        userBalance.setTransaction(transaction);
        userBalance.setUser(user);
        userBalance.setSum(product.getPrice());

        userBalance.setTransactionDate(getDate());

        userBalanceDAO.insert(userBalance);

    }
    private java.sql.Date getDate(){
        Calendar c = new GregorianCalendar();
        java.util.Date utilDate = c.getTime();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

}
