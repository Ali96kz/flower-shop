package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.dao.manager.CachedDAO;
import com.epam.az.flower.shop.entity.Flower;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.service.ServiceException;

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

    public List<Integer> getAllByPrice(int min, int max) throws DAOException {
        try {
            String sql = "SELECT Product.id FROM Product Where Product.price < "+max +" and Product.price > "+min;
            ResultSet resultSet = executeSQL(sql);
            return parserResultSet(resultSet);
        } catch (DAOException e) {
            throw new DAOException("", e);
        }
    }

    public List<Integer> getAllByAverageHeight(int min, int max) throws DAOException {
        try {
            String sql = "SELECT Product.id FROM Product " +
                    "INNER JOIN Flower ON Product.flowerId = Flower.id AND Flower.growingConditionId < "+max + " and Flower.averageHeight > " + min;

            ResultSet resultSet = executeSQL(sql);
            return parserResultSet(resultSet);
        } catch (DAOException e) {
            throw new DAOException("", e);
        }
    }


    public List<Integer> getAllByGrowingCondition(int id) throws ServiceException {
        try {
            String sql = "SELECT Product.id FROM Product " +
                    "INNER JOIN Flower ON Product.flowerId = Flower.id AND Flower.growingConditionId= "+id;
            ResultSet resultSet = executeSQL(sql);
            return parserResultSet(resultSet);
        } catch (DAOException e) {
            throw new ServiceException("", e);
        }
    }

    public List<Integer> getAllByFlowerType(int id) throws ServiceException {
        try {
            String sql = "SELECT Product.id FROM Product " +
                    "INNER JOIN Flower ON Product.flowerId = Flower.id AND Flower.flowerTypeId= "+id;
            ResultSet resultSet = executeSQL(sql);
            return parserResultSet(resultSet);
        } catch (DAOException e) {
            throw new ServiceException("", e);
        }
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

