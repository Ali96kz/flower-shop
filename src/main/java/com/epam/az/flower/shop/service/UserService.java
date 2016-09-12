package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.dao.UserRoleDao;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserRole;

import java.util.List;

public class UserService {
    private final String CUSTOMER_USER_ROLE = "customer";
    DAOFactory daoFactory = DAOFactory.getInstance();
    UserDAO userDAO = daoFactory.getDao(UserDAO.class);
    UserRoleDao userRoleDao = daoFactory.getDao(UserRoleDao.class);
    TransactionService transactionService = new TransactionService();

    public void addMoneyToBalance(User user, int summ) throws ServiceException {
        user.setBalance(user.getBalance() + summ);
        userDAO.update(user);
        transactionService.addMoneyTransaction(user, summ);
    }

    public void delete(int userId) {
        userDAO.delete(userId);
    }

    public User registerUser(User user) throws ServiceException {
        UserRole userRole ;
        try {
            userRole = userRoleDao.findUserRoleByName(CUSTOMER_USER_ROLE);
        } catch (DAOException e) {
            throw new ServiceException("can't get user role bu name", e);
        }
        user.setUserRole(userRole);
        int index = userDAO.insert(user);
        user.setId(index);
        return user;
    }

    public User findById(int id) throws ServiceException {
        User user ;
        try {
            user = userDAO.findById(id);
            UserRole userRole = userRoleDao.findById(user.getUserRole().getId());
            user.setUserRole(userRole);
        } catch (DAOException e) {
            throw new ServiceException("Can't get user by id", e);
        }

        return user;
    }
    public List<User> getAll(){
        return userDAO.getAll();
    }
    public Integer getUserByCredentials(String nickName, String passHash) {
        Integer id = userDAO.findByCredentials(nickName, passHash);
        return id;
    }

    public void logout(int id) {
        userDAO.deleteFromCache(id);
    }

    public void update(User user) {
        userDAO.update(user);
    }
}
