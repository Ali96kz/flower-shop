package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public abstract class AbstractDAO<E extends BaseEntity> implements DAO<E> {
    private Connection connection;
    private Class genericClass;
    private Logger logger = LoggerFactory.getLogger(AbstractDAO.class);
    private SQLExecutor sqlExecutor = new SQLExecutor();
    private SQLCreator sqlCreator = new SQLCreator();
    private SQLFiller sqlFiller = new SQLFiller();
    private SQLParser sqlParser = new SQLParser();

    @Override
    public void delete(int id) throws DAOException {
        try {
            String sql = sqlCreator.createSQLForDelete(id, genericClass);
            PreparedStatement preparedStatement = sqlFiller.fillDeleteStatement(connection.prepareStatement(sql));
            sqlExecutor.executeSql(sql, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(E item) throws DAOException {
        try {
            String sql = sqlCreator.createSQLForDelete(item);
            PreparedStatement preparedStatement = sqlFiller.fillDeleteStatement(connection.prepareStatement(sql));
            sqlExecutor.executeSql(sql, preparedStatement);
        } catch (SQLException e) {
            throw new DAOException("can't execute", e);
        }
    }

    @Override
    public E findById(int id) throws DAOException {
        try {
            E result = getGenericClass().newInstance();
            String selectSQL = sqlCreator.createSqlForFindById(genericClass, id);
            ResultSet resultSet = sqlExecutor.executeSqlQuery(selectSQL, connection.createStatement());

            if (resultSet.next())
                result = (E) sqlParser.parseResultSet(result, resultSet);

            return result;
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            throw new DAOException("can't get user by id", e);
        }
    }

    @Override
    public int insert(E e) throws DAOException {
        StringBuilder sql = new StringBuilder();
        StringBuilder values = new StringBuilder();
        sqlCreator.createSqlForPreparedStatement(sql, values, e);
        String insertSQL = sql + ")values(" + values + ");";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            sqlFiller.fillPrepareStatementForInsert(preparedStatement, e);
            int generatedId = sqlExecutor.executeSql(sql ,preparedStatement);
        } catch (SQLException e1) {
            throw new DAOException("", e1);
        }
        return
    }

    @Override
    public void update(E item) throws DAOException {
        String sql = sqlCreator.createSQLForUpdate(item);

        sqlExecutor.executeSql( );
    }

    @Override
    public List<E> getAll() {
        List<E> resultList = new ArrayList<>();
        String selectSQL = sqlCreator.createSQL(getGenericClass());

        try {
            ResultSet resultSet = sqlExecutor.executeSqlQuery("SELECT " + selectSQL + ";", connection);
            while (resultSet.next()) {
                E e = (E) sqlParser.parseResultSet(getGenericClass().newInstance(), resultSet);
                resultList.add(e);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    private Class<E> getGenericClass() {
        if (genericClass == null) {
            Type mySuperClass = getClass().getGenericSuperclass();
            Type tType = ((ParameterizedType) mySuperClass).getActualTypeArguments()[0];
            String className = tType.toString().split(" ")[1];
            try {
                genericClass = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return genericClass;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setGenericClass(Class genericClass) {
        this.genericClass = genericClass;
    }
}
