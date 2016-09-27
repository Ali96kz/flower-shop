package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class SQLExecutor <E extends BaseEntity> extends AbstractSQLManager{
    private static Logger logger = LoggerFactory.getLogger(SQLExecutor.class);

    public int executeSql(PreparedStatement statement) throws DAOException {
        int result = 0;
        try {
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("can't execute sql", e);
        }
        return result;
    }
    public int executePreapareSql(Statement statement) throws DAOException {
        int result = 0;
        try {
            statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("can't execute sql", e);
        }
        return result;
    }


    public ResultSet executeSqlQuery(String sql, Statement statement) throws DAOException {
        logger.info("execute sql query {}", sql);
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(sql.toString());
            return resultSet;
        } catch (SQLException e1) {
            throw new DAOException("can't execute sql", e1);
        }
    }

    public int executeSql(String sql, Connection connection) throws DAOException {
        logger.info("dao sql exe sql  = {}", sql);
        int result = 0;
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("can't execute sql", e);
        }
        return result;
    }
    public int executePrepareSql(String sql, E object, Connection connection) throws DAOException {
        logger.info("dao sql exe sql  = {}", sql);
        int result = 0;
        try {
            logger.info("execute prepare sql = {}", sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();

        } catch (SQLException e) {
            throw new DAOException("can't execute sql", e);
        }
        return result;
    }
    public int executePrepareSqlWithGeneratedKeys(String sql, E object, Connection connection) throws DAOException {
        logger.info("dao sql exe sql  = {}", sql);
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("can't execute sql", e);
        }
        return result;
    }
    public ResultSet executeSqlQuery(String sql, Connection connection) throws DAOException {
        logger.info("execute sql query {}", sql);
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql.toString());
            return resultSet;
        } catch (SQLException e1) {
            throw new DAOException("can't execute sql", e1);
        }
    }


}
