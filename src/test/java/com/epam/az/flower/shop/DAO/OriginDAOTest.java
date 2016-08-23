package com.epam.az.flower.shop.DAO;

import com.epam.az.flower.shop.entity.Origin;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class OriginDAOTest {
    OriginDAO originDAO = new OriginDAO();
    Origin origin = new Origin();

    @Before
    public void init() {
        origin.setCountry("TestExampleCountry");
        origin.setProvince("TestExampleProvince");
    }

    @Test
    public void testInsert() throws SQLException {
        int id = originDAO.insert(origin);
        ResultSet resultSet = executeSqlQuery("SELECT id, province, country, deleteDay FROM " +
                "Origin WHERE id = " + id);
        assertTrue("Empty ResultSet", resultSet.next());

        assertEquals("Incorrect province", origin.getProvince(), resultSet.getString("province"));
        assertEquals("Incorrect country", origin.getCountry(), resultSet.getString("country"));
        assertEquals("Incorrect id", id, resultSet.getString("id"));
        assertNull("Delete day not null", resultSet.getString("deleteDay"));

    }

    public void execute() {

    }


    public ResultSet executeSqlQuery(String sql) {
        Statement statement;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flowershoptest", "root", "root");
            statement = connection.createStatement();
            System.out.println(sql.toString());
            resultSet = statement.executeQuery(sql.toString());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}
