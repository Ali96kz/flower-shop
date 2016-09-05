package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.UserBalanceDAO;
import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.dao.UserRoleDao;
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
    UserRoleDao userRoleDao = daoFactory.getDao(UserRoleDao.class);
    TransactionService transactionService = new TransactionService();
    public void addMoneyToBalance(User user, int summ) {
        user.setBalance(user.getBalance() + summ);
        userDAO.update(user);
        transactionService.addMoneyTransaction(user, summ);
    }
    public void delete(int userId){
        userDAO.delete(userId);
    }

    public User registerUser(User user) {
        UserRole userRole = new UserRole();
        userRole.setId(4);
        user.setUserRole(userRole);
        int index = userDAO.insert(user);
        user.setId(index);
        return user;
    }

    public User findByID(int id) {
        User user = userDAO.findById(id);
        UserRole userRole = userRoleDao.findById(user.getUserRole().getId());
        user.setUserRole(userRole);
        return userDAO.findById(id);
    }

    public Integer getUserByCredentials(String nickName, String passHash) {
        Integer id = userDAO.findByCredentials(nickName, passHash);
        return id;
    }

    public void logout(int id){
        userDAO.deleteFromCache(id);
    }

    public void update(User user) {
        userDAO.update(user);
    }
}
