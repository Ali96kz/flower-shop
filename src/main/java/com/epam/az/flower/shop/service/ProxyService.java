package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.manager.AbstractDAO;
import com.epam.az.flower.shop.dao.manager.CachedDAO;
import com.epam.az.flower.shop.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * This class delete template code from another service
 * Method insert, delete and update executes in transaction
 *
 * @param <E>
 */
public class ProxyService<E extends CachedDAO> {
    private static Logger logger = LoggerFactory.getLogger(ProxyService.class);

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private Class<E> daoClass;

    public ProxyService(Class<E> daoClass) {
        this.daoClass = daoClass;
    }

    /**
     * Set connection into dao class
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    public BaseEntity findById(int id) throws ServiceException {
        E abstractDAO = null;
        try {
            abstractDAO = daoFactory.getDao(daoClass);
            daoFactory.startOperation(abstractDAO);
            BaseEntity baseEntity = abstractDAO.findById(id);
            return baseEntity;
        } catch (DAOException e) {
            logger.error("can't find user by id", e);
            throw new ServiceException("can't find by id", e);
        } finally {
            daoFactory.endOperation(abstractDAO);
        }
    }

    /**
     * Set connection to dao object
     * this operations executes in transaction
     *
     * @param baseEntity
     * @return generated key
     * @throws ServiceException
     */
    public int insert(BaseEntity baseEntity) throws ServiceException {
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
                logger.error("can't rollback transaction", e);
                throw new ServiceException("can't rollback transaction", e);
            }
            logger.error("can't insert new object", e);
            throw new ServiceException("can't insert", e);
        }
    }

    public void update(BaseEntity entity) throws ServiceException {
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
                logger.error("can't rollback transaction", e1);
                throw new ServiceException("can't rollback transaction", e1);
            }
        }

    }

    public void delete(BaseEntity baseEntity) throws ServiceException {
        delete(baseEntity.getId());
    }

    public void delete(int id) throws ServiceException {
        E abstracDao = null;
        try {
            abstracDao = daoFactory.getDao(daoClass);
            daoFactory.startTransaction(abstracDao);
            abstracDao.delete(id);
            daoFactory.commitTransaction(abstracDao);
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(abstracDao);
                logger.error("can't rollback transaction", e);

            } catch (DAOException e1) {
                logger.error("can't execute transaction", e1);
                throw new ServiceException("can't execute transaction", e);
            }
        }
    }

    public List<BaseEntity> getAll() throws ServiceException {
        E abstractDao = null;
        try {
            abstractDao = daoFactory.getDao(daoClass);
            daoFactory.startOperation(abstractDao);
            List<BaseEntity> baseEntities = abstractDao.getAll();
            return baseEntities;
        } catch (DAOException e) {
            logger.error("can't get all", e);
            throw new ServiceException("can't get all", e);
        } finally {
            daoFactory.endOperation(abstractDao);
        }
    }
}