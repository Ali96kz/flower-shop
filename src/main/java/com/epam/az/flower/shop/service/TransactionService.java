package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.TransactionDAO;
import com.epam.az.flower.shop.entity.Transaction;

public class TransactionService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private TransactionDAO transactionDAO = daoFactory.getDao(TransactionDAO.class);

    public Transaction getTransactionByName(String name) throws ServiceException {
        try {
            daoFactory.startOperation(transactionDAO);
            Transaction transaction = transactionDAO.getTransactionByName(name);

            return transaction;
        } catch (DAOException e) {
            throw new ServiceException("", e);
        } finally {
            daoFactory.endOperation(transactionDAO);
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
