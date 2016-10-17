package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.manager.AbstractDAO;
import com.epam.az.flower.shop.entity.BaseEntity;

public class ProxyService <E extends AbstractDAO>{
    private DAOFactory daoFactory = DAOFactory.getInstance();

    public BaseEntity findById(Class daoClass, int id ) throws ServiceException {
        AbstractDAO abstractDAO = null;
        try {
            abstractDAO = daoFactory.getDao(daoClass);
            daoFactory.startOperation(abstractDAO);
            BaseEntity baseEntity = abstractDAO.findById(id);
            return baseEntity;
        } catch (DAOException e) {
            throw new ServiceException("can't find by id", e);
        }finally {
            daoFactory.endOperation(abstractDAO);
        }
    }
    public int insert(Class daoClass, BaseEntity baseEntity) throws ServiceException{
        AbstractDAO abstractDAO = null;
        try {
            abstractDAO = daoFactory.getDao(daoClass);
            daoFactory.startTransaction(abstractDAO);
            int id = abstractDAO.insert(baseEntity);
            daoFactory.commitTransaction(abstractDAO);
            return id;
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(abstractDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't rollback transaction", e);
            }
            throw new ServiceException("can't insert", e);
        }
    }

}
