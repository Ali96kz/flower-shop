package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.*;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserRole;

import java.util.List;

public class UserService {
    private final String CUSTOMER_USER_ROLE = "customer";
    private UserTransactionService userTransactionService = new UserTransactionService();
    private UserRoleService userRoleService = new UserRoleService();

    public void addMoneyToBalance(User user, int summ) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                UserDAO userDAO = daoFactory.createDAO(UserDAO.class);
                daoFactory.startTransaction();
                user.setBalance(user.getBalance() + summ);
                userDAO.update(user);
                userTransactionService.addMoneyTransaction(user, summ);
                daoFactory.commitTransaction();
            } catch (DAOException e) {
                daoFactory.rollBack();
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public void delete(int userId) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                UserDAO userDAO = daoFactory.createDAO(UserDAO.class);
                daoFactory.startTransaction();
                userDAO.delete(userId);
                daoFactory.commitTransaction();
            } catch (DAOException e) {
                daoFactory.rollBack();
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public User registerUser(User user) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                UserDAO userDAO = daoFactory.createDAO(UserDAO.class);
                daoFactory.startTransaction();
                UserRole userRole;
                userRole = userRoleService.getUserRoleByName(CUSTOMER_USER_ROLE);
                user.setUserRole(userRole);
                int index = userDAO.insert(user);
                user.setId(index);
                daoFactory.commitTransaction();
                return user;
            } catch (DAOException e) {
                daoFactory.rollBack();
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public User findById(int id) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                UserDAO userDAO = daoFactory.createDAO(UserDAO.class);
                User user = userDAO.findById(id);
                UserRole userRole = userRoleService.findById(user.getUserRole().getId());
                user.setUserRole(userRole);
                return user;
            } catch (DAOException e) {
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public List<User> getAll() throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                UserDAO userDAO = daoFactory.createDAO(UserDAO.class);
                return userDAO.getAll();
            } catch (DAOException e) {
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public Integer getUserByCredentials(String nickName, String passHash) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                UserDAO userDAO = daoFactory.createDAO(UserDAO.class);
                daoFactory.startTransaction();
                Integer userID = userDAO.findByCredentials(nickName, passHash);
                daoFactory.commitTransaction();
                return userID;
            } catch (DAOException e) {
                daoFactory.rollBack();
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public void logout(int id) {
    }

    public void update(User user) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                UserDAO userDAO = daoFactory.createDAO(UserDAO.class);
                daoFactory.startTransaction();
                userDAO.update(user);
                daoFactory.commitTransaction();
            } catch (DAOException e) {
                daoFactory.rollBack();
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }
}
