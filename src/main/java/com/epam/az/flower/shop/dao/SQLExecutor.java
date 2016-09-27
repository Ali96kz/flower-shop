package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Field;
import java.sql.*;

public class SQLExecutor <E extends BaseEntity> extends AbstractSQLManager{
    private static Logger logger = LoggerFactory.getLogger(SQLExecutor.class);
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
            fillPrepareStatement(statement, object);
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
            fillPrepareStatement(statement, object);
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

    public void fillPrepareStatement(PreparedStatement preparedStatement, E object) {
        Field[] fields = object.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value = fields[i].get(object);
                if (value instanceof String) {
                    preparedStatement.setString(i + 1, (String) value);
                } else if (value instanceof Integer) {
                    preparedStatement.setInt(i + 1, (Integer) value);
                } else if (value instanceof Date) {
                    Date date = (Date) value;
                    preparedStatement.setDate(i + 1, date);
                } else if (value instanceof Boolean) {
                    preparedStatement.setBoolean(i + 1, (Boolean) value);
                } else if (value instanceof BaseEntity) {
                    int id = getObjectId(value);
                    preparedStatement.setInt(i + 1, id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
