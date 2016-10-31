package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.dao.manager.CachedDAO;
import com.epam.az.flower.shop.entity.Product;
import com.sun.glass.ui.EventLoop;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends CachedDAO<Product> {

    public int[] findProductIdByOriginID(int id) throws DAOException {
        setGenericClass(Product.class);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT Product.id FROM Product WHERE originId = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = getSqlExecutor().executePreparedSqlQuery(preparedStatement);
            int[] productId = parseResultSet(resultSet);
            return productId;
        } catch (SQLException e) {
            throw new DAOException("can't create prepare statement", e);
        }
    }

    public int[] findProductIdByPrice(int min, int max) throws DAOException {
        setGenericClass(Product.class);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT Product.id FROM Product WHERE price > ? AND  price < ?");
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);

            ResultSet resultSet = getSqlExecutor().executePreparedSqlQuery(preparedStatement);
            int[] productId = parseResultSet(resultSet);
            return productId;
        } catch (SQLException e) {
            throw new DAOException("can't create prepare statement", e);
        }
    }



    private int[] parseResultSet(ResultSet resultSet) throws SQLException {
        int[] productId = new int[50];
        int i = 0;

        if (resultSet.next()) {
            productId[i] = resultSet.getInt("Product.id");
            i++;
            if (i == productId.length) {
                int[] cparr = new int[productId.length + 50];
                for (int ki = 0; ki < productId.length; ki++) {
                    cparr[ki] = productId[ki];
                }
                productId = cparr;
            }
        }

        return productId;
    }
}

