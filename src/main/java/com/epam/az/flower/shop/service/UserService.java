package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.DAO.UniersalDAO;
import com.epam.az.flower.shop.entity.User;

public class    UserService {
    private UniersalDAO userDAO = new UniersalDAO(User.class);
    public void addMoneyToBalance(User user, int sum){

    }
    public void transferMoney(User user1, User user2){

    }
    public int registerUser(User user){
        int index = userDAO.insert(user);
        return index;
    }
    public User getUserByID(int id){
        return (User) userDAO.findById(id);
    }

}
