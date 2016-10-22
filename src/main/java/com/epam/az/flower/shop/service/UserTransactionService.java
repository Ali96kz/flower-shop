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
    private static final Class<UserTransactionDAO> USER_TRANSACTION_DAO_CLASS = UserTransactionDAO.class;
    private static Logger logger = LoggerFactory.getLogger(UserTransactionService.class);
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserTransactionDAO userTransactionDAO = daoFactory.getDao(USER_TRANSACTION_DAO_CLASS);
    private TransactionService transactionService = new TransactionService();
    private ProxyService proxyService = new ProxyService(USER_TRANSACTION_DAO_CLASS);

    public List<UserTransaction> getAll(int userId) throws ServiceException {
        List<UserTransaction> userTransactions;
        try {
            daoFactory.startOperation(userTransactionDAO);
            userTransactions = userTransactionDAO.getAll(userId);
            for (UserTransaction userTransaction : userTransactions) {
                Transaction transaction = transactionService.findById(userTransaction.getTransaction().getId());
                userTransaction.setTransaction(transaction);
            }
        } catch (DAOException e) {
            throw new ServiceException("Can't get all user transactions", e);
        } finally {
            daoFactory.endOperation(userTransactionDAO);
        }

        return userTransactions;
    }

    public int insert(UserTransaction userTransaction) throws ServiceException {
        int id = proxyService.insert(userTransaction);
        return id;
    }

    public void addMoneyTransaction(User user, int summ) throws ServiceException {
        UserTransaction userTransaction = new UserTransaction();
        Transaction transaction = transactionService.getTransactionByName(ADD_MONEY_TRANSACTION_NAME);

        logger.info("Find transaction by name {}", transaction.getName());

        userTransaction.setTransaction(transaction);
        userTransaction.setUser(user);
        userTransaction.setSum(summ);
        userTransaction.setTransactionDate(getDate());

        proxyService.insert(userTransaction);
    }

    private java.sql.Date getDate() {
        Calendar c = new GregorianCalendar();
        java.util.Date utilDate = c.getTime();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

}
