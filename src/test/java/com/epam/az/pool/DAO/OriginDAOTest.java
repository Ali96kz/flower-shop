package com.epam.az.pool.DAO;

import com.epam.az.pool.entity.Origin;
import com.epam.az.pool.pool.ConnectionPool;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OriginDAOTest {
    static AbstractDAO originDAO;
    static ConnectionPool connectionPool;
    static Origin origin;
    @BeforeClass
    public static void init(){
        originDAO  = new OriginDAO();
        connectionPool = ConnectionPool.getInstance();
    }
    @Test
    public void insertTest() throws SQLException {
        Origin origin = new Origin();
        origin.setProvince("Almaty");
        origin.setCountry("Kazakhstan");
        int id = originDAO.insert(origin);
        Statement statement = connectionPool.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, country, province FROM Origin WHERE ID = " + id);
        if (resultSet.next()){
            assertEquals("Incorrect id", id, resultSet.getInt("id"));
            assertEquals("", origin.getCountry(), resultSet.getString("country"));
            assertEquals("", origin.getProvince(), resultSet.getString("province"));
        }
    }
    @Test
    public void updateTest() throws SQLException {
        Origin origin = new Origin();
        origin.setId(1);
        origin.setProvince("Volgograd");
        origin.setCountry("Russia");
        originDAO.update(origin);

        Statement statement = connectionPool.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, country, province FROM Origin WHERE ID = " + origin.getId());
        if (resultSet.next()){
            assertEquals("Incorrect id", origin.getId(), resultSet.getInt("id"));
            assertEquals("Didn't update", origin.getCountry(), resultSet.getString("country"));
            assertEquals("Didn't update", origin.getProvince(), resultSet.getString("province"));
        }
    }

    @Test
    public void findByIdTest() throws SQLException {
        Origin origin = (Origin) originDAO.findById(1);
        Statement statement = connectionPool.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, country, province FROM Origin WHERE ID = " + 1);
        if (resultSet.next()){
            assertEquals("Incorrect id", origin.getId(), resultSet.getInt("id"));
            assertEquals("Incorrect country", origin.getCountry(), resultSet.getString("country"));
            assertEquals("Incorrect province", origin.getProvince(), resultSet.getString("province"));
        }
    }
    @Test
    public void deleteTest() throws SQLException {
        Origin origin = new Origin();
        origin.setId(1);
        origin.setProvince("Volgograd");
        origin.setCountry("Russia");
        originDAO.delete(origin);
        Statement statement = connectionPool.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT deleteDAY FROM Origin WHERE ID = " + origin.getId());

        if (resultSet.next()){
            assertNotNull("Origin doesn't delete", origin.getDeleteDate());
        }
    }
}
