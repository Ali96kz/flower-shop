package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactory implements AutoCloseable {
    private ConnectionPool connectionPool = ConnectionPool.getInstancePool();
    private Connection connection = connectionPool.getConnection();

    public <E> E createDAO(Class aClass) throws DAOException {
        try {
            AbstractDAO abstractDAO =  (AbstractDAO) aClass.newInstance();
            abstractDAO.setConnection(connection);
            return (E) abstractDAO;
        } catch (InstantiationException e) {
            throw new DAOException("can't create dao class", e);
        } catch (IllegalAccessException e) {
            throw new DAOException("can't get access to this class", e);
        }
    }

    public void startTransaction() throws DAOException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException("can't execute sql", e);
        }
    }

    public void commitTransaction() throws DAOException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DAOException("can't commit transaction", e);
        } finally {
        }
    }

    public void rollBack() throws DAOException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DAOException("can't roll back transaction", e);
        }
    }

    @Override
    public void close() throws Exception {

    }
}
