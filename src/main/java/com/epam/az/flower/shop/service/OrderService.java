package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.OrderDAO;
import com.epam.az.flower.shop.entity.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class OrderService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private OrderDAO orderDAO = daoFactory.getDao(OrderDAO.class);
    private UserService userService = new UserService();
    private UserTransactionService userTransactionService = new UserTransactionService();
    private Transaction transaction = new Transaction();

    public void createOrder(User user, Product product) throws ServiceException {
        try {
            UserOrder userOrder = new UserOrder();
            userOrder.setUser(user);
            userOrder.setProduct(product);
            userOrder.setOrderDate(getDate());
            daoFactory.startTransaction(orderDAO);
            orderDAO.insert(userOrder);
            daoFactory.commitTransaction(orderDAO);

            user.setBalance(user.getBalance() - product.getPrice());
            userService.update(user);

            transaction.setId(2);
            UserTransaction userTransaction = new UserTransaction();
            userTransaction.setTransaction(transaction);
            userTransaction.setUser(user);
            userTransaction.setSum(product.getPrice());

            userTransaction.setTransactionDate(getDate());
            userTransactionService.insert(userTransaction);
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(orderDAO);
            } catch (DAOException e1) {
                throw new ServiceException("transaction roll back service", e);
            }
            throw new ServiceException("can't create order", e);
        }

    }

    private java.sql.Date getDate() {
        Calendar c = new GregorianCalendar();
        java.util.Date utilDate = c.getTime();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

}
