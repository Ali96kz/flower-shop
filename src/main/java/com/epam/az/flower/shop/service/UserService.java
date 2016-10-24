package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserRole;

import java.util.List;

public class UserService {
    private final String CUSTOMER_USER_ROLE = "customer";
    private static final Class<UserDAO> USER_DAO_CLASS = UserDAO.class;
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO;
    private UserRoleService userRoleService = new UserRoleService();
    private UserTransactionService userTransactionService = new UserTransactionService();
    private ProxyService proxyService = new ProxyService(USER_DAO_CLASS);

    public void init() throws ServiceException {
        try {
            if (userDAO == null)
                userDAO = daoFactory.getDao(USER_DAO_CLASS);
        } catch (DAOException e) {
            throw new ServiceException("can't get dao class", e);
        }
    }

    public void addMoneyToBalance(User user, int sum) throws ServiceException {
        user.setBalance(user.getBalance() + sum);
        userTransactionService.addMoneyTransaction(user, sum);
        proxyService.update(user);
    }

    public boolean isFree(String name) throws ServiceException {
        try {
            init();
            daoFactory.startOperation(userDAO);
            Integer id = userDAO.findByCredentials(name);
            if (id == null || id == 0) {
                return true;
            }

            return false;
        } catch (DAOException e) {
            throw new ServiceException("can't execute", e);
        } finally {
            daoFactory.endOperation(userDAO);
        }
    }

    public void delete(int userId) throws ServiceException {
        proxyService.delete(userId);
    }

    public User registerUser(User user) throws ServiceException {
        UserRole userRole = userRoleService.getUserRoleByName(CUSTOMER_USER_ROLE);
        user.setUserRole(userRole);
        int userId = proxyService.insert(user);
        user.setId(userId);
        return user;
    }

    public User findById(int id) throws ServiceException {
        User user = (User) proxyService.findById(id);
        UserRole userRole = userRoleService.findById(user.getUserRole().getId());
        user.setUserRole(userRole);
        return user;
    }

    public List<User> getAllActiveUsers() throws ServiceException {
        List<User> users = proxyService.getAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getDeleteDay() != null)
                users.remove(i);
        }
        return users;
    }

    public void logout(int id) {
        userDAO.deleteFromCache(id);
    }

    public void update(User user) throws ServiceException {
        proxyService.update(user);
    }

    public Integer getUserIdByCredentials(String nickName, String password) throws ServiceException {
        try {
            init();
            daoFactory.startOperation(userDAO);
            Integer id = userDAO.findByCredentials(nickName, password);
            return id;
        } catch (DAOException e) {
            throw new ServiceException("can't find user by credentials", e);
        } finally {
            daoFactory.endOperation(userDAO);
        }
    }
}
