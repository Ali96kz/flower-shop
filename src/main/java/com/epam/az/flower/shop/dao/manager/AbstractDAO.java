package com.epam.az.flower.shop.dao.manager;

import com.epam.az.flower.shop.dao.DAO;
import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<E extends BaseEntity> implements DAO<E> {
    private Connection connection;
    private SQLExecutor sqlExecutor = new SQLExecutor();
    private SQLCreator sqlCreator = new SQLCreator();
    private PrepareStatementFiller prepareStatementFiller = new PrepareStatementFiller();
    private ResultSetParser resultSetParser = new ResultSetParser();
    private Class genericClass;
    private Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

    /**
     * This method don't delete information from DB,
     * just set deleted day
     */
    @Override
    public void delete(int id) throws DAOException {
        try {
            String sql = sqlCreator.createSQLForDelete(id, getGenericClass());
            logger.info("delete sql {}", sql);
            PreparedStatement preparedStatement = prepareStatementFiller.fillDeleteStatement(connection.prepareStatement(sql));
            sqlExecutor.executeSql(preparedStatement);
        } catch (SQLException e) {
            throw new DAOException("can't create delete statement", e);
        }
    }

    /**
     * This method don't delete information from DB,
     * just set deleted day
     */
    @Override
    public void delete(E item) throws DAOException {
        try {
            String sql = sqlCreator.createSQLForDelete(item);
            PreparedStatement preparedStatement = prepareStatementFiller.fillDeleteStatement(connection.prepareStatement(sql));
            sqlExecutor.executeSqlWithGeneratedKeys(preparedStatement);
        } catch (SQLException e) {
            throw new DAOException("can't execute", e);
        }
    }

    @Override
    public E findById(int id) throws DAOException {
        try {
            E result = getGenericClass().newInstance();
            String selectSQL = sqlCreator.createStatementSqlForFindById(getGenericClass(), id);
            ResultSet resultSet = sqlExecutor.executeSqlQuery(selectSQL, connection.createStatement());

            if (resultSet.next())
                result = (E) resultSetParser.parseResultSet(result, resultSet);

            return result;
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            throw new DAOException("can't get object by id", e);
        }
    }

    /**
     * Insert into database.
     */
    @Override
    public int insert(E e) throws DAOException {
        String insertSQL = sqlCreator.createPrepareInsertSQL(e.getClass());

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            prepareStatementFiller.fillPrepareStatement(preparedStatement, e);
            int generatedKey = sqlExecutor.executeSqlWithGeneratedKeys(preparedStatement);

            return generatedKey;
        } catch (SQLException e1) {
            throw new DAOException("can't execute", e1);
        }
    }

    @Override
    public void update(E item) throws DAOException {
        String sql = sqlCreator.createSQLForUpdate(item);
        logger.info("Update sql {}", sql);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatementFiller.fillPrepareStatement(preparedStatement, item);
            sqlExecutor.executeSqlWithGeneratedKeys(preparedStatement);
        } catch (SQLException e) {
            throw new DAOException("can't execute update sql", e);
        }
    }

    @Override
    public List<E> getAll() throws DAOException {
        List<E> resultList = new ArrayList<>();
        String selectSQL = sqlCreator.createSqlForGetAll(getGenericClass());

        ResultSet resultSet;
        try {
            resultSet = sqlExecutor.executeSqlQuery(selectSQL, connection.createStatement());
            while (resultSet.next()) {
                E e = (E) resultSetParser.parseResultSet(getGenericClass().newInstance(), resultSet);
                resultList.add(e);
            }

            return resultList;
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            throw new DAOException("can;t execute sql", e);
        }
    }

    protected Class<E> getGenericClass() throws DAOException {
        if (genericClass == null) {
            Type mySuperClass = getClass().getGenericSuperclass();
            Type tType = ((ParameterizedType) mySuperClass).getActualTypeArguments()[0];
            String className = tType.toString().split(" ")[1];
            try {
                genericClass = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new DAOException("can't get generic class", e);
            }
        }
        return genericClass;
    }

    public void setGenericClass(Class genericClass) {
        this.genericClass = genericClass;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public SQLExecutor getSqlExecutor() {
        return sqlExecutor;
    }

    public SQLCreator getSqlCreator() {
        return sqlCreator;
    }

    public PrepareStatementFiller getPrepareStatementFiller() {
        return prepareStatementFiller;
    }

    public ResultSetParser getResultSetParser() {
        return resultSetParser;
    }
}
