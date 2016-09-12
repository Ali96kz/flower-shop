package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.*;
import com.epam.az.flower.shop.entity.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class OrderService {
    DAOFactory daoFactory = DAOFactory.getInstance();
    private OrderDAO orderDAO;
    private UserDAO userDAO;
    private UserBalanceDAO userBalanceDAO;
    Transaction transaction = new Transaction();

    public OrderService() throws ServiceException {
        try {
            userDAO = daoFactory.getDao(UserDAO.class);
            userBalanceDAO = daoFactory.getDao(UserBalanceDAO.class);
            orderDAO = daoFactory.getDao(OrderDAO.class);
        } catch (DAOException e) {
            throw new ServiceException("can't initialize dao classes", e);
        }
    }

    public void createOrder(User user, Product product) {
        try {
            UserOrder userOrder = new UserOrder();
            userOrder.setUser(user);
            userOrder.setProduct(product);
            userOrder.setOrderDate(getDate());
            orderDAO.insert(userOrder);

            user.setBalance(user.getBalance() - product.getPrice());
            userDAO.update(user);

            transaction.setId(2);
            UserTransaction userTransaction = new UserTransaction();
            userTransaction.setTransaction(transaction);
            userTransaction.setUser(user);
            userTransaction.setSum(product.getPrice());

            userTransaction.setTransactionDate(getDate());
            userBalanceDAO.insert(userTransaction);
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    private java.sql.Date getDate() {
        Calendar c = new GregorianCalendar();
        java.util.Date utilDate = c.getTime();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

}
