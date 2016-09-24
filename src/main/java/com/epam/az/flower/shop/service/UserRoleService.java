package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.UserRoleDao;
import com.epam.az.flower.shop.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserRoleService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserRoleDao userRoleDao ;
    private static Logger logger = LoggerFactory.getLogger(UserRoleService.class);
    public UserRoleService() throws ServiceException {
        try {
            userRoleDao = daoFactory.getDao(UserRoleDao.class);
        } catch (DAOException e) {
            throw new ServiceException("can't initialize class", e);
        }
    }
    public List<UserRole> getAll() {
        return userRoleDao.getAll();
    }

    public UserRole findById(int id) throws ServiceException {
        UserRole userRole;
        try {
            daoFactory.startOperation(userRoleDao);
            System.out.println(userRoleDao.getConnection()+" user role dao get connection");
            userRole = userRoleDao.findById(id);
            logger.info("get user role by id {}", id);
        } catch (DAOException e) {
            throw new ServiceException("can't find user role by id", e);
        }finally {
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
            throw new ServiceException("can't commit transaction");
        }finally {
            daoFactory.endOperation(userRoleDao);
        }
    }
}
