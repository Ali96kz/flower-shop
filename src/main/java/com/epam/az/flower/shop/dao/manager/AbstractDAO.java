package com.epam.az.flower.shop.dao.manager;

import com.epam.az.flower.shop.dao.DAO;
import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;
/**Create, and execute sql.
 *
 * */

public abstract class AbstractDAO<E extends BaseEntity> implements DAO<E> {
    protected Connection connection;
    private Class genericClass;
    private Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

    protected SQLExecutor sqlExecutor = new SQLExecutor();
    protected PrepareSQLCreator prepareSqlCreator = new PrepareSQLCreator();
    protected SQLFiller sqlFiller = new SQLFiller();
    protected SQLParser sqlParser = new SQLParser();

    @Override
    public void delete(int id) throws DAOException {
        try {
            String sql = prepareSqlCreator.createSQLForDelete(id, genericClass);
            logger.info("delete sql {}",sql);
            PreparedStatement preparedStatement = sqlFiller.fillDeleteStatement(connection.prepareStatement(sql));
            sqlExecutor.executeSql(preparedStatement);
        } catch (SQLException e) {
        }
    }

    @Override
    public void delete(E item) throws DAOException {
        try {
            String sql = prepareSqlCreator.createSQLForDelete(item);
            PreparedStatement preparedStatement = sqlFiller.fillDeleteStatement(connection.prepareStatement(sql));
            sqlExecutor.executeSqlWithGeneratedKeys(preparedStatement);
        } catch (SQLException e) {
            throw new DAOException("can't execute", e);
        }
    }

    @Override
    public E findById(int id) throws DAOException {
        try {
            E result = getGenericClass().newInstance();
            String selectSQL = prepareSqlCreator.createSqlForFindById(genericClass, id);
            ResultSet resultSet = sqlExecutor.executeSqlQuery(selectSQL, connection.createStatement());

            if (resultSet.next())
                result = (E) sqlParser.parseResultSet(result, resultSet);

            return result;
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            throw new DAOException("can't get object by id", e);
        }
    }

    @Override
    public int insert(E e) throws DAOException {

        String insertSQL = prepareSqlCreator.createInsertSQL(e.getClass());

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            sqlFiller.fillPrepareStatement(preparedStatement, e);
            int generatedId = sqlExecutor.executeSqlWithGeneratedKeys(preparedStatement);
            return generatedId;
        } catch (SQLException e1) {
            throw new DAOException("", e1);
        }
    }

    @Override
    public void update(E item) throws DAOException {
        String sql = prepareSqlCreator.createSQLForUpdate(item);
        logger.info("Update sql {}", sql);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            sqlFiller.fillPrepareStatement(preparedStatement, item);
            sqlExecutor.executeSqlWithGeneratedKeys(preparedStatement);
        } catch (SQLException e) {
            throw new DAOException("can't execute update sql", e);
        }
    }

    @Override
    public List<E> getAll() throws DAOException {
        List<E> resultList = new ArrayList<>();
        String selectSQL = prepareSqlCreator.createSqlForGetAll(genericClass);

        ResultSet resultSet ;
        try {
            resultSet = sqlExecutor.executeSqlQuery(selectSQL, connection.createStatement());
            while (resultSet.next()) {
                E e = (E) sqlParser.parseResultSet(getGenericClass().newInstance(), resultSet);
                resultList.add(e);
            }

            return resultList;
        } catch (SQLException | InstantiationException | IllegalAccessException  e) {
            throw new DAOException("can;t execute sql", e);
        }
    }

    protected Class<E> getGenericClass() {
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
