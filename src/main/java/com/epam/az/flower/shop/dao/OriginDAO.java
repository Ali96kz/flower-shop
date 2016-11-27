package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.dao.manager.CachedDAO;
import com.epam.az.flower.shop.entity.Origin;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OriginDAO extends CachedDAO<Origin> {
    public void executeProcedure(String country, String province) {
        try {
            String sqlCall = "{call insertOrigin(?, ?)}";
            CallableStatement cstmt = getConnection().prepareCall(sqlCall);
            cstmt.setString("provincce", province);
            cstmt.setString("scountry", country);
            cstmt.execute();
        } catch (SQLException e) {
        }
    }

    public int countAddMoney(int id, int idTransaction) throws DAOException {
        try {
            String sqlCall = "{call countAddMoney(?, ?)}";
            CallableStatement cstmt = getConnection().prepareCall(sqlCall);
            cstmt.setInt("id", id);
            cstmt.setInt("idTransaction", idTransaction);

            ResultSet resultSet = cstmt.executeQuery();
            int result = 0;
            if (resultSet.next()) {
                result = resultSet.getInt("totalsum");
            }

            return result;
        } catch (SQLException e) {
            throw new DAOException("", e);
        }
    }
}
