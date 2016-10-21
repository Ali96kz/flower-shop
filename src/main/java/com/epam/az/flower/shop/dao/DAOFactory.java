package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.dao.manager.AbstractDAO;
import com.epam.az.flower.shop.pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DAOFactory {
    private static DAOFactory daoFactory = new DAOFactory();
    private static Logger logger = LoggerFactory.getLogger(DAOFactory.class);
    private Map<Class, AbstractDAO> daoClassMap = new HashMap<>();
    private ConnectionPool connectionPool = new ConnectionPool();

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return daoFactory;
    }

    public <E> E getDao(Class aClass) {
        if (daoClassMap.get(aClass) == null) {
            try {
                AbstractDAO abstractDAO = (AbstractDAO) aClass.newInstance();
                daoClassMap.put(aClass, abstractDAO);
            } catch (InstantiationException e) {
                logger.error("can't get dao class from dao", e);
            } catch (IllegalAccessException e) {
                logger.error("try to get access to private field", e);
            }
        }
        return (E) daoClassMap.get(aClass);
    }

    public <E extends AbstractDAO> void startOperation(E dao) throws DAOException {
        try {
            logger.info("start operation {}", dao.getClass().getSimpleName());
            dao.setConnection(connectionPool.getConnection());
        } catch (SQLException e) {
            throw new DAOException("can't set connection", e);
        }
    }

    public <E extends AbstractDAO> void endOperation(E dao) {
        try {
            logger.info("end operation {}", dao.getClass().getSimpleName());
            dao.getConnection().close();
            dao.setConnection(null);
        } catch (SQLException e) {

        }
    }

    public <E extends AbstractDAO> void startTransaction(E dao) throws DAOException {

        Connection connection;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new DAOException("can't set connection", e);
        }

        dao.setConnection(connection);

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException("can't execute sql", e);
        }
    }

    public <E extends AbstractDAO> void commitTransaction(E dao) throws DAOException {
        Connection connection = dao.getConnection();
        try {
            connection.commit();
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("can't commit transaction", e);
        } finally {
        }
    }

    public <E extends AbstractDAO> void rollBack(E dao) throws DAOException {
        Connection connection = dao.getConnection();
        try {
            connection.rollback();
            connection.close();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DAOException("can't roll back transaction", e);
        }
    }
}
