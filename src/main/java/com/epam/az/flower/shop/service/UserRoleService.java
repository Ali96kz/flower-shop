package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.UserRoleDao;
import com.epam.az.flower.shop.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserRoleService {
    private static Logger logger = LoggerFactory.getLogger(UserRoleService.class);
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserRoleDao userRoleDao = daoFactory.getDao(UserRoleDao.class);

    public List<UserRole> getAll() throws ServiceException {
        try {
            daoFactory.startOperation(userRoleDao);
            return userRoleDao.getAll();
        } catch (DAOException e) {
            throw new ServiceException("can't get all userRole", e);
        } finally {
            daoFactory.endOperation(userRoleDao);
        }
    }

    public UserRole findById(int id) throws ServiceException {
        UserRole userRole;
        try {
            daoFactory.startOperation(userRoleDao);
            System.out.println(userRoleDao.getConnection() + " user role dao get connection");
            userRole = userRoleDao.findById(id);
            logger.info("get user role by id {}", id);
        } catch (DAOException e) {
            throw new ServiceException("can't find user role by id", e);
        } finally {
            daoFactory.endOperation(userRoleDao);
        }
        return userRole;
    }

    public UserRole getUserRoleByName(String roleName) throws ServiceException {
        try {
            daoFactory.startOperation(userRoleDao);
            UserRole userRole = userRoleDao.findUserRoleByName(roleName);
            return userRole;
        } catch (DAOException e) {
            throw new ServiceException("can't commit transaction", e);
        } finally {
            daoFactory.endOperation(userRoleDao);
        }
    }
}
