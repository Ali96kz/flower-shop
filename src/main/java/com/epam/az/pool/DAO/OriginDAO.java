package com.epam.az.pool.DAO;

import com.epam.az.pool.entity.Origin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OriginDAO extends AbstractDAO<Origin> {
    @Override
    public Origin findById(int id) {
        Origin origin = new Origin();
        String selectSQL = createSelectSQL(Origin.class);
        ResultSet resultSet = executeSqlQuery(selectSQL + "where id = " + id);
        try {
            if (resultSet.next())
                parseResultSet(origin, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return origin;
    }

    @Override
    public List<Origin> getAll() {
        List<Origin> resultList = new ArrayList<>();
        String selectSQL = createSelectSQL(Origin.class);
        try {
            ResultSet resultSet = executeSqlQuery(selectSQL + ";");
            while (resultSet.next()) {
                Origin origin = parseResultSet(new Origin(), resultSet);
                resultList.add(origin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

}
