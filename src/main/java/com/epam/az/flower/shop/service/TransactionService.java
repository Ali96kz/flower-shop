package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.TransactionDAO;
import com.epam.az.flower.shop.entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TransactionService {
    private static final Class<TransactionDAO> TRANSACTION_DAO_CLASS = TransactionDAO.class;
    private Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private TransactionDAO transactionDAO;
    private ProxyService proxyService = new ProxyService(TRANSACTION_DAO_CLASS);

    public void initDao() throws ServiceException {
        try {
            if (transactionDAO == null) {
                transactionDAO = daoFactory.getDao(TRANSACTION_DAO_CLASS);
            }
        } catch (DAOException e) {
            logger.error("can't initialize transaction dao", e);
            throw new ServiceException("can't get transaction Dao", e);
        }
    }

    public Transaction getTransactionByName(String name) throws ServiceException {
        try {
            initDao();
            daoFactory.startOperation(transactionDAO);
            Transaction transaction = transactionDAO.getTransactionByName(name);
            return transaction;
        } catch (DAOException e) {
            logger.error("can't get transaction by name", e);
            throw new ServiceException("can't get transaction by name", e);
        } finally {
            daoFactory.endOperation(transactionDAO);
        }
    }

    public Transaction findById(Integer id) throws ServiceException {
        Transaction transaction = (Transaction) proxyService.findById(id);
        return transaction;
    }

    public List<Transaction> getAll() throws ServiceException {
        List<Transaction> transactions = proxyService.getAll();
        return transactions;
    }
}
