package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.entity.UserTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserTransactionDAO extends CachedDAO<UserTransaction> {

    public List<UserTransaction> getAll(int id) throws DAOException {
        List<UserTransaction> resultList = new ArrayList<>();
        String selectSQL = createSQL(UserTransaction.class);
        try {
            ResultSet resultSet = executeSqlQuery("SELECT " + selectSQL +
                    " where userId = " + id + ";");
            while (resultSet.next()) {
                UserTransaction e = parseResultSet(getGenericClass().newInstance(), resultSet);
                resultList.add(e);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            throw new DAOException("Can't get user from database",e);
        }

        return resultList;
    }
}
