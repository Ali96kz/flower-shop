package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.dao.UserTransactionDAO;
import com.epam.az.flower.shop.entity.Transaction;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserTransaction;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class UserTransactionService {
    private static final String ADD_MONEY_TRANSACTION_NAME = "add money";
    private TransactionService transactionService = new TransactionService();

    public List<UserTransaction> getAll(int userId) throws ServiceException {
        List<UserTransaction> userTransactions ;
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                UserTransactionDAO userTransactionDAO = daoFactory.createDAO(UserTransactionDAO.class);
                userTransactions = userTransactionDAO.getAll(userId);
                for (UserTransaction userTransaction : userTransactions) {
                    Transaction transaction = transactionService.findById(userTransaction.getTransaction().getId());
                    userTransaction.setTransaction(transaction);
                }
                return userTransactions;
            } catch (DAOException e) {
                daoFactory.rollBack();
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public int insert(UserTransaction userTransaction) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                UserTransactionDAO userTransactionDAO = daoFactory.createDAO(UserTransactionDAO.class);
                daoFactory.startTransaction();
                int id = userTransactionDAO.insert(userTransaction);
                daoFactory.commitTransaction();
                return id;
            } catch (DAOException e) {
                daoFactory.rollBack();
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public void addMoneyTransaction(User user, int summ) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                UserTransactionDAO userTransactionDAO = daoFactory.createDAO(UserTransactionDAO.class);
                Transaction transaction = transactionService.getTransactionByName(ADD_MONEY_TRANSACTION_NAME);

                UserTransaction userTransaction = new UserTransaction();
                daoFactory.startTransaction();
                userTransaction.setTransaction(transaction);
                userTransaction.setUser(user);
                userTransaction.setSum(summ);
                userTransaction.setTransactionDate(getDate());
                userTransactionDAO.insert(userTransaction);

                daoFactory.commitTransaction();
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
