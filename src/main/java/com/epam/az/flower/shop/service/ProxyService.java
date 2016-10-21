package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.manager.AbstractDAO;
import com.epam.az.flower.shop.entity.BaseEntity;
import com.epam.az.flower.shop.entity.Origin;

import java.util.List;

public class ProxyService <E extends AbstractDAO>{
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private Class <E> daoClass;

    public ProxyService(Class <E> daoClass){
        this.daoClass = daoClass;
    }

    public BaseEntity findById(int id ) throws ServiceException {
        E abstractDAO = null;
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

    public int insert(BaseEntity baseEntity) throws ServiceException{
        E abstractDAO = null;
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

    public void update(BaseEntity entity) throws ServiceException{
        E abstractDAO = null;
        try {
            abstractDAO = daoFactory.getDao(daoClass);
            daoFactory.startTransaction(abstractDAO);
            abstractDAO.update(entity);
            daoFactory.commitTransaction(abstractDAO);
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(abstractDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't rollback transaction", e);
            }
            throw new ServiceException("can't update object", e);
        }

    }

    public void delete (BaseEntity baseEntity) throws ServiceException {
        delete(baseEntity.getId());
    }

    public void delete(int id) throws ServiceException {
        E abstracDao = daoFactory.getDao(daoClass);
        try {
            daoFactory.startTransaction(abstracDao);
            abstracDao.delete(id);
            daoFactory.commitTransaction(abstracDao);
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(abstracDao);
            } catch (DAOException e1) {
                throw new ServiceException("can't execute transaction", e);
            }
        }
    }

    public List<BaseEntity> getAll() throws ServiceException {
        E abstractDao = daoFactory.getDao(daoClass);

        try {
            daoFactory.startOperation(abstractDao);
            List<BaseEntity> baseEntities = abstractDao.getAll();
            return baseEntities;
        } catch (DAOException e) {
            throw new ServiceException("can't get all", e);
        }finally {
            daoFactory.endOperation(abstractDao);
        }
    }
}