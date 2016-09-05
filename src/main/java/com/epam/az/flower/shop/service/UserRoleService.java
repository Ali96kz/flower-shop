package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.UserBalanceDAO;
import com.epam.az.flower.shop.dao.UserRoleDao;
import com.epam.az.flower.shop.entity.UserRole;

import java.util.List;

public class UserRoleService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserRoleDao userRoleDao = daoFactory.getDao(UserRoleDao.class);
    public List<UserRole> getAll(){
        return userRoleDao.getAll();
    }
    public UserRole findById(int id){
        return userRoleDao.findById(id);
    }
}
