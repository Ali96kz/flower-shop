package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.DAO.UserDAO;
import com.epam.az.flower.shop.entity.User;

public class    UserService {
    private UserDAO userDAO = new UserDAO();
    public void addMoneyToBalance(User user, int sum){

    }
    public void transferMoney(User user1, User user2){

    }

    public int registerUser(User user){

        int index = userDAO.insert(user);

        return index;
    }

    public User getUserByID(int id){
        return userDAO.findById(id);
    }

    public Integer getUserByCredentials(String nickName, String passHash){
        Integer id = userDAO.findByCredentials(nickName, passHash);
        return id;
    }

}
