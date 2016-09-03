package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.UserBalanceDAO;
import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.entity.Transaction;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserBalance;
import com.epam.az.flower.shop.entity.UserRole;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class UserService {
    DAOFactory daoFactory = DAOFactory.getInstance();
    UserDAO userDAO = daoFactory.getDao(UserDAO.class);
    UserBalanceDAO balanceDAO = daoFactory.getDao(UserBalanceDAO.class);

    public void addMoneyToBalance(User user, int summ) {
        user.setBalance(user.getBalance() + summ);
        userDAO.update(user);
        Transaction transaction = new Transaction();
        transaction.setId(3);

        UserBalance userBalance = new UserBalance();
        userBalance.setTransaction(transaction);
        userBalance.setUser(user);
        userBalance.setSum(summ);
        userBalance.setTransactionDate(getDate());
        balanceDAO.insert(userBalance);

    }

    public int registerUser(User user) {
        UserRole userRole = new UserRole();
        userRole.setId(4);
        user.setUserRole(userRole);
        int index = userDAO.insert(user);
        return index;
    }

    public User getUserByID(int id) {
        return userDAO.findById(id);
    }

    public Integer getUserByCredentials(String nickName, String passHash) {
        Integer id = userDAO.findByCredentials(nickName, passHash);
        return id;
    }

    public void logout(int id){
        userDAO.deleteFromCache(id);
    }

    public List<UserBalance> getAllUserTransaction(int userId){
        List<UserBalance> userBalances = balanceDAO.getAll(userId);
        return userBalances;
    }
    private java.sql.Date getDate(){
        Calendar c = new GregorianCalendar();
        java.util.Date utilDate = c.getTime();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

}
