package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.UserBalanceDAO;
import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserBalance;
import com.epam.az.flower.shop.entity.UserRole;

import java.util.List;

public class UserService {
    DAOFactory daoFactory = DAOFactory.getInstance();
    UserDAO userDAO = daoFactory.getDao(UserDAO.class);
    UserBalanceDAO balanceDAO = daoFactory.getDao(UserBalanceDAO.class);

    public void addMoneyToBalance(User user, int summ) {
        user.setBalance(user.getBalance() + summ);
        userDAO.update(user);
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
}
