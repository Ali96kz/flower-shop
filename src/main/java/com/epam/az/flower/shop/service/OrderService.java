package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.*;
import com.epam.az.flower.shop.entity.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class OrderService {
    private UserService userService;
    private UserTransactionService userTransactionService;
    private TransactionService transactionService;
    public OrderService() {
        userService = new UserService();
        transactionService = new TransactionService();
        userTransactionService = new UserTransactionService();
    }

    public void createOrder(User user, Product product) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                UserOrder userOrder = new UserOrder();
                userOrder.setUser(user);
                userOrder.setProduct(product);
                userOrder.setOrderDate(getDate());
                user.setBalance(user.getBalance() - product.getPrice());
                userService.update(user);

                daoFactory.startTransaction();
                UserOrderDAO userOrderDAO = daoFactory.createDAO(UserOrderDAO.class);
                userOrderDAO.insert(userOrder);
                daoFactory.commitTransaction();

                Transaction transaction = transactionService.getTransactionByName("buy product");
                UserTransaction userTransaction = new UserTransaction();
                userTransaction.setTransaction(transaction);
                userTransaction.setUser(user);
                userTransaction.setSum(product.getPrice());

                userTransaction.setTransactionDate(getDate());
                userTransactionService.insert(userTransaction);
            } catch (DAOException e) {
                daoFactory.rollBack();
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    private java.sql.Date getDate() {
        Calendar c = new GregorianCalendar();
        java.util.Date utilDate = c.getTime();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

}
