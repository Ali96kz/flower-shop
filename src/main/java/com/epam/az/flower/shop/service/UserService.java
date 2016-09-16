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
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO;

    private UserRoleDao userRoleDao;
    private UserTransactionService userTransactionService;
    public UserService() throws ServiceException {
        try {
            userDAO = daoFactory.getDao(UserDAO.class);
            userRoleDao = daoFactory.getDao(UserRoleDao.class);
            userTransactionService = new UserTransactionService();
        } catch (DAOException e) {
            throw new ServiceException("can't initialize class", e);
        }

    }

    public void addMoneyToBalance(User user, int summ) throws ServiceException {
        user.setBalance(user.getBalance() + summ);
        try {
            userDAO.update(user);
            userTransactionService.addMoneyTransaction(user, summ);
        } catch (DAOException e) {
            throw new ServiceException("can't update user");
        }
    }

    public void delete(int userId) throws ServiceException {
        try {
            userDAO.delete(userId);
        } catch (DAOException e) {
            throw new ServiceException("can't delete user from DB", e);
        }
    }

    public User registerUser(User user) throws ServiceException {
        UserRole userRole;
        try {
            userRole = userRoleDao.findUserRoleByName(CUSTOMER_USER_ROLE);
            user.setUserRole(userRole);
            int index = userDAO.insert(user);
            user.setId(index);
            return user;
        } catch (DAOException e) {
            throw new ServiceException("can't get user role bu name", e);
        }
    }

    public User findById(int id) throws ServiceException {
        User user;
        try {
            user = userDAO.findById(id);
            UserRole userRole = userRoleDao.findById(user.getUserRole().getId());
            user.setUserRole(userRole);
        } catch (DAOException e) {
            throw new ServiceException("Can't get user by id", e);
        }

        return user;
    }

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public Integer getUserByCredentials(String nickName, String passHash) throws ServiceException {
        Integer id ;
        try {
            id = userDAO.findByCredentials(nickName, passHash);
        } catch (DAOException e) {
            throw new ServiceException("can't find user by credentials", e);
        }
        return id;
    }

    public void logout(int id) {
        userDAO.deleteFromCache(id);
    }

    public void update(User user) throws ServiceException {
        try {
            userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException("can't update user", e);
        }
    }
}
