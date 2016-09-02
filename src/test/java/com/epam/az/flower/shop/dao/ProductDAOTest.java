package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.pool.ConnectionPool;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductDAOTest {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    @Test
    public void getAllTest(){
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getAll();
    }
    public ResultSet executeQuery(String sql) throws SQLException {
        Connection connection = connectionPool.getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }
}
