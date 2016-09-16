package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.TransactionDAO;
import com.epam.az.flower.shop.dao.UserTransactionDAO;
import com.epam.az.flower.shop.entity.Transaction;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserTransaction;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TransactionService {
    DAOFactory daoFactory = DAOFactory.getInstance();
    TransactionDAO transactionDAO ;

    public TransactionService() throws ServiceException {
        try {
            transactionDAO = daoFactory.getDao(TransactionDAO.class);
        } catch (DAOException e) {
            throw new ServiceException("can't initialize dao class", e);
        }
    }
    public Transaction getTransactionByName(String name) throws ServiceException {
        try {
            daoFactory.startTransaction(transactionDAO);
            Transaction transaction = transactionDAO.getTransactionByName(name);
            daoFactory.commitTransaction(transactionDAO);
            return transaction;
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(transactionDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't roll back", e);
            }
            throw new ServiceException("", e);
        }
    }

    public Transaction findById(Integer id) throws ServiceException {
        try {
            daoFactory.startTransaction(transactionDAO);
            Transaction transaction = transactionDAO.findById(id);
            daoFactory.commitTransaction(transactionDAO);
            return transaction;
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(transactionDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't roll back", e);
            }
            throw new ServiceException("", e);
        }
    }

}
