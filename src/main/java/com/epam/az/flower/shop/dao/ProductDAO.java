package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.dao.manager.CachedDAO;
import com.epam.az.flower.shop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends CachedDAO<Product> {

    public List<Integer> getAllByVisual(int id) throws DAOException {

        String sql = "SELECT Product.id FROM Product " +
                "INNER JOIN Flower ON Product.flowerId = Flower.id AND Flower.visualParametersId= "+id;
        ResultSet resultSet = executeSQL(sql);

        return parserResultSet(resultSet);
    }

    private ResultSet executeSQL(String sql) throws DAOException {
        try {
            Statement statement = getConnection().createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new DAOException("", e);
        }
    }

    private List<Integer> parserResultSet(ResultSet resultSet) throws DAOException {
        try {
            List<Integer> nmber = new ArrayList<>();
            while (resultSet.next()) {
                nmber.add(resultSet.getInt("Product.id"));
            }

            return nmber;
        } catch (SQLException e) {
            throw new DAOException("", e);
        }
    }
}

