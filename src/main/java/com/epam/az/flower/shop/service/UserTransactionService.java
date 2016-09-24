package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.UserTransactionDAO;
import com.epam.az.flower.shop.entity.Transaction;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class UserTransactionService {
    private static final String ADD_MONEY_TRANSACTION_NAME = "add money";
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserTransactionDAO userTransactionDAO;
    private TransactionService transactionService;
    private static Logger logger = LoggerFactory.getLogger(UserTransactionService.class);
    public UserTransactionService() throws ServiceException {
        try {
            transactionService = new TransactionService();
            userTransactionDAO = daoFactory.getDao(UserTransactionDAO.class);
        } catch (DAOException e) {
            throw new ServiceException("", e);
        }
    }


    public List<UserTransaction> getAll(int userId) throws ServiceException {
        List<UserTransaction> userTransactions ;
        try {
            userTransactions = userTransactionDAO.getAll(userId);
            for (UserTransaction userTransaction : userTransactions) {
                Transaction transaction = transactionService.findById(userTransaction.getTransaction().getId());
                userTransaction.setTransaction(transaction);
            }
        } catch (DAOException e) {
            throw new ServiceException("Can't get all user transactions", e);
        }

        return userTransactions;
    }

    public int insert(UserTransaction userTransaction) throws ServiceException {
        try {
            daoFactory.startTransaction(userTransactionDAO);
            int id = userTransactionDAO.insert(userTransaction);
            daoFactory.commitTransaction(userTransactionDAO);
            return id;
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(userTransactionDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't rollback transaction", e);
            }
            throw new ServiceException("can't execute transaction", e);
        }
    }

    public void addMoneyTransaction(User user, int summ) throws ServiceException {
        UserTransaction userTransaction = new UserTransaction();
        Transaction transaction;
        try {
            daoFactory.startTransaction(userTransactionDAO);
            transaction = transactionService.getTransactionByName(ADD_MONEY_TRANSACTION_NAME);
            userTransaction.setTransaction(transaction);
            userTransaction.setUser(user);
            userTransaction.setSum(summ);
            userTransaction.setTransactionDate(getDate());
            userTransactionDAO.insert(userTransaction);

            daoFactory.commitTransaction(userTransactionDAO);
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(userTransactionDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't rollback transaction", e1);
            }
            throw new ServiceException("can't add money", e);
        }
    }

    private java.sql.Date getDate() {
        Calendar c = new GregorianCalendar();
        java.util.Date utilDate = c.getTime();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

}
