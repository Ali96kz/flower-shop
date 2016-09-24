package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.dao.UserRoleDao;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserService {
    private final String CUSTOMER_USER_ROLE = "customer";
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO;
    private UserRoleService userRoleService;
    private UserTransactionService userTransactionService;
    private static final Logger lo = LoggerFactory.getLogger(UserService.class);
    public UserService() throws ServiceException {
        try {
            userDAO = daoFactory.getDao(UserDAO.class);
            userRoleService = new UserRoleService();
            userTransactionService = new UserTransactionService();
        } catch (DAOException e) {
            throw new ServiceException("can't initialize class", e);
        }

    }

    public void addMoneyToBalance(User user, int sum) throws ServiceException {
        user.setBalance(user.getBalance() + sum);
        try {
            daoFactory.startTransaction(userDAO);

            userTransactionService.addMoneyTransaction(user, sum);
            userDAO.update(user);

            daoFactory.commitTransaction(userDAO);
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(userDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't rollback transaction", e);
            }
            throw new ServiceException("can't update user");
        }
    }

    public void delete(int userId) throws ServiceException {
        try {
            daoFactory.startOperation(userDAO);
            userDAO.delete(userId);

        } catch (DAOException e) {
            throw new ServiceException("can't delete user from DB", e);
        }finally {
            daoFactory.endOperation(userDAO);
        }
    }

    public User registerUser(User user) throws ServiceException {
        UserRole userRole;
        try {
            userRole = userRoleService.getUserRoleByName(CUSTOMER_USER_ROLE);
            user.setUserRole(userRole);

            daoFactory.startTransaction(userDAO);
            lo.info("user role id {}", userRole.getId());
            int index = userDAO.insert(user);
            user.setId(index);
            daoFactory.commitTransaction(userDAO);
            return user;
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(userDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't roll back transaction", e1);
            }
            throw new ServiceException("can't get user role bu name", e);
        }
    }

    public User findById(int id) throws ServiceException {
        User user;
        try {
            daoFactory.startOperation(userDAO);
            user = userDAO.findById(id);
            UserRole userRole = userRoleService.findById(user.getUserRole().getId());
            user.setUserRole(userRole);
        } catch (DAOException e) {
            throw new ServiceException("Can't get user by id", e);
        }finally {
            daoFactory.endOperation(userDAO);

        }

        return user;
    }

    public List<User> getAll() throws ServiceException {
        try {
            daoFactory.startOperation(userDAO);
            List<User> users=  userDAO.getAll();
            return users;
        } catch (DAOException e) {
            throw new ServiceException("can't get all user from dao", e);
        }finally {
            daoFactory.endOperation(userDAO);
        }
    }

    public Integer getUserByCredentials(String nickName, String passHash) throws ServiceException {
        Integer id ;
        try {
            daoFactory.startOperation(userDAO);
            id = userDAO.findByCredentials(nickName, passHash);
        } catch (DAOException e) {
            throw new ServiceException("can't find user by credentials", e);
        }finally {
            daoFactory.endOperation(userDAO);
        }
        return id;
    }

    public void logout(int id) {
        userDAO.deleteFromCache(id);
    }

    public void update(User user) throws ServiceException {
        try {
            daoFactory.startTransaction(userDAO);
            userDAO.update(user);
            daoFactory.commitTransaction(userDAO);
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(userDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't rollback transaction");
            }
            throw new ServiceException("can't update user", e);
        }
    }
}
