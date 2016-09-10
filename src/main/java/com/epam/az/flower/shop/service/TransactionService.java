package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.TransactionDAO;
import com.epam.az.flower.shop.dao.UserBalanceDAO;
import com.epam.az.flower.shop.entity.Transaction;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserTransaction;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TransactionService {
    DAOFactory daoFactory = DAOFactory.getInstance();
    UserBalanceDAO balanceDAO = daoFactory.getDao(UserBalanceDAO.class);
    TransactionDAO transactionDAO = new TransactionDAO();
    public void addMoneyTransaction(User user, int summ){
        Transaction transaction = new Transaction();
        UserTransaction userTransaction = new UserTransaction();

        transaction.setId(3);
        userTransaction.setTransaction(transaction);
        userTransaction.setUser(user);
        userTransaction.setSum(summ);
        userTransaction.setTransactionDate(getDate());
        balanceDAO.insert(userTransaction);
    }

    public List<UserTransaction> getAllUserTransaction(int userId) throws ServiceException {
        List<UserTransaction> userTransactions = null;
        try {
            userTransactions = balanceDAO.getAll(userId);
        } catch (DAOException e) {
            throw new ServiceException("Can't get all user transactions", e);
        }
        for (UserTransaction userTransaction : userTransactions) {
            Transaction transaction ;
            try {
                transaction = transactionDAO.findById(userTransaction.getTransaction().getId());
            } catch (DAOException e) {
                throw new ServiceException("can't find transaction by id", e);
            }
            userTransaction.setTransaction(transaction);
        }
        return userTransactions;
    }

    private java.sql.Date getDate(){
        Calendar c = new GregorianCalendar();
        java.util.Date utilDate = c.getTime();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }
}
