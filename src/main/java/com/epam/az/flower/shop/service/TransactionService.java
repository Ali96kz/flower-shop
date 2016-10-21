package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.TransactionDAO;
import com.epam.az.flower.shop.entity.Transaction;

public class TransactionService {
    public static final Class<TransactionDAO> TRANSACTION_DAO_CLASS = TransactionDAO.class;
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private TransactionDAO transactionDAO = daoFactory.getDao(TRANSACTION_DAO_CLASS);
    private ProxyService proxyService = new ProxyService(TRANSACTION_DAO_CLASS);

    public Transaction getTransactionByName(String name) throws ServiceException {
        try {
            daoFactory.startOperation(transactionDAO);
            Transaction transaction = transactionDAO.getTransactionByName(name);
            return transaction;
        } catch (DAOException e) {
            throw new ServiceException("can't get transaction by name", e);
        } finally {
            daoFactory.endOperation(transactionDAO);
        }
    }

    public Transaction findById(Integer id) throws ServiceException {
        Transaction transaction = (Transaction) proxyService.findById(id);
        return transaction;
    }

}
