package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.UserRoleDao;
import com.epam.az.flower.shop.dao.UserTransactionDAO;
import com.epam.az.flower.shop.entity.UserRole;

import java.util.List;

public class UserRoleService {
    public List<UserRole> getAll() throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                UserRoleDao userRoleDao = daoFactory.createDAO(UserRoleDao.class);
                return userRoleDao.getAll();
            } catch (DAOException e) {
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }

    }

    public UserRole findById(int id) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                UserRoleDao userRoleDao = daoFactory.createDAO(UserRole.class);
                UserRole userRole = userRoleDao.findById(id);
                return userRole;
            } catch (DAOException e) {
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public UserRole getUserRoleByName(String roleName) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                UserRoleDao userRoleDao = daoFactory.createDAO(UserRole.class);
                UserRole userRole = userRoleDao.findUserRoleByName(roleName);
                return userRole;
            } catch (DAOException e) {

                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }

    }
}
