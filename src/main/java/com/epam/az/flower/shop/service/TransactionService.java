package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.TransactionDAO;
import com.epam.az.flower.shop.dao.UserBalanceDAO;
import com.epam.az.flower.shop.entity.Transaction;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserBalance;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TransactionService {
    DAOFactory daoFactory = DAOFactory.getInstance();
    UserBalanceDAO balanceDAO = daoFactory.getDao(UserBalanceDAO.class);
    TransactionDAO transactionDAO = new TransactionDAO();
    public void addMoneyTransaction(User user, int summ){
        Transaction transaction = new Transaction();
        UserBalance userBalance = new UserBalance();
        transaction.setId(3);
        userBalance.setTransaction(transaction);
        userBalance.setUser(user);
        userBalance.setSum(summ);
        userBalance.setTransactionDate(getDate());

    }

    public List<UserBalance> getAllUserTransaction(int userId) {
        List<UserBalance> userBalances = balanceDAO.getAll(userId);
        for (UserBalance userBalance : userBalances) {
            Transaction transaction = transactionDAO.findById(userBalance.getTransaction().getId());
            userBalance.setTransaction(transaction);
        }
        return userBalances;
    }

    private java.sql.Date getDate(){
        Calendar c = new GregorianCalendar();
        java.util.Date utilDate = c.getTime();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }
}
