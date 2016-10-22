package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.UserRoleDao;
import com.epam.az.flower.shop.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserRoleService {
    private static final Class<UserRoleDao> USER_ROLE_DAO_CLASS = UserRoleDao.class;
    private static Logger logger = LoggerFactory.getLogger(UserRoleService.class);
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private ProxyService proxyService = new ProxyService(USER_ROLE_DAO_CLASS);
    private UserRoleDao userRoleDao = daoFactory.getDao(USER_ROLE_DAO_CLASS);

    public List<UserRole> getAll() throws ServiceException {
        return proxyService.getAll();
    }

    public UserRole findById(int id) throws ServiceException {
        UserRole userRole = (UserRole) proxyService.findById(id);
        logger.info("get user role by id {}", id);
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
