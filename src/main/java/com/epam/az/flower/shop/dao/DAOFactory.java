package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DAOFactory {
    private Map<Class, AbstractDAO> daoClassMap = new HashMap<>();
    private static DAOFactory daoFactory = new DAOFactory();
    private ConnectionPool connectionPool = new ConnectionPool();
    private static Logger logger = LoggerFactory.getLogger(DAOFactory.class);
    private DAOFactory() {

    }

    public static DAOFactory getInstance(){
        return daoFactory;
    }

    public <E> E getDao(Class aClass) throws DAOException {
        if (daoClassMap.get(aClass) == null) {
            try {
                AbstractDAO abstractDAO = (AbstractDAO) aClass.newInstance();
                abstractDAO.setConnection(connectionPool.getConnection());
                daoClassMap.put(aClass, abstractDAO);
            } catch (InstantiationException e) {
                throw new DAOException("can't create instance of that class", e);
            } catch (IllegalAccessException e) {
                throw new DAOException("field or class is private", e);
            } catch (SQLException e) {
                throw new DAOException("in correct sql", e);
            }

        }
        return (E) daoClassMap.get(aClass);
    }

    public <E extends AbstractDAO> void startOperation(E dao) throws DAOException {
        try {
            logger.info("start operation");
            dao.setConnection(connectionPool.getConnection());
        } catch (SQLException e) {
            throw new DAOException("can't set connection", e);
        }
    }
    public <E extends AbstractDAO> void endOperation(E dao) {
        try {
            dao.getConnection().close();
            dao.setConnection(null);
            logger.info(dao.getConnection()+"dao connection after close connection");
        } catch (SQLException e) {

        }
    }

    public <E extends AbstractDAO> void startTransaction(E dao) throws DAOException {
        Connection connection = dao.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException("can't execute sql", e);
        }
    }

    public <E extends AbstractDAO>  void commitTransaction(E dao) throws DAOException {
        Connection connection = dao.getConnection();
        try {
            connection.commit();
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("can't commit transaction", e);
        }finally {
        }
    }

    public<E extends AbstractDAO> void rollBack(E dao) throws DAOException {
        Connection connection = dao.getConnection();
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("can't roll back transaction", e);
        }
    }

}
