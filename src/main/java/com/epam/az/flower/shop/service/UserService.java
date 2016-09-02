package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserRole;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public void addMoneyToBalance(User user, int summ) {
        user.setBalance(user.getBalance() + summ);
        userDAO.update(user);
    }

    public void transferMoney(User user1, User user2) {
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


}
