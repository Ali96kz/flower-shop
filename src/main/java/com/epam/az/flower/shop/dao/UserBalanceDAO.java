package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.entity.UserBalance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBalanceDAO extends CachedDAO<UserBalance> {

    public List<UserBalance> getAll(int id) {
        List<UserBalance> resultList = new ArrayList<>();
        String selectSQL = createSQL(UserBalance.class);
        try {
            ResultSet resultSet = executeSqlQuery("SELECT " + selectSQL +
                    " where userId = " + id + ";");
            while (resultSet.next()) {
                UserBalance e = parseResultSet(getGenericClass().newInstance(), resultSet);
                resultList.add(e);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
