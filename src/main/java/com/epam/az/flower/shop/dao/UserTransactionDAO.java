package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.dao.manager.CachedDAO;
import com.epam.az.flower.shop.entity.UserTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserTransactionDAO extends CachedDAO<UserTransaction> {
    private static final Logger logger = LoggerFactory.getLogger(UserTransactionDAO.class);

    public List<UserTransaction> getAll(int id) throws DAOException {
        List<UserTransaction> resultList = new ArrayList<>();
        String selectSQL = getSqlCreator().createSQL(UserTransaction.class);

        try {
            ResultSet resultSet = getSqlExecutor().executeSqlQuery("SELECT " + selectSQL +
                    " where userId = " + id + ";", getConnection().createStatement());
            while (resultSet.next()) {
                UserTransaction e = (UserTransaction) getResultSetParser().parseResultSet(getGenericClass().newInstance(), resultSet);
                resultList.add(e);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            logger.error("can't get user by name", e);
            throw new DAOException("Can't get user from database", e);
        }

        return resultList;
    }
}
