package com.epam.az.flower.shop.dao.manager;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class SQLExecutor <E extends BaseEntity> extends AbstractSQLManager{
    private static Logger logger = LoggerFactory.getLogger(SQLExecutor.class);

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
    public ResultSet executePreaparedSqlQuery(PreparedStatement statement) throws DAOException {
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException e1) {
            throw new DAOException("can't execute sql", e1);
        }
    }

    public int executeSqlWithGeneratedKeys(PreparedStatement preparedStatement) throws DAOException {
        int result = 0;
        try {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException("can't execute sql", e);
        }
    }
    public void executeSql(PreparedStatement preparedStatement) throws DAOException {
        int result = 0;
        try {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("can't execute sql", e);
        }
    }
}
