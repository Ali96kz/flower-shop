package com.epam.az.flower.shop.DAO;

import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.pool.ConnectionPool;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class UserDAOTest {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    @Test
    public void testFindById() throws SQLException {
        int id = 1;
        UniersalDAO uniersalDAO = new UniersalDAO(User.class);
        User user = (User) uniersalDAO.findById(id);
        checkWithDatabaseVersion(user);
    }
    @Test
    public void  testUpdate(){
    }

    public void checkWithDatabaseVersion(User user) throws SQLException {
        ResultSet resultSet = executeQuery("SELECT id, lastName, nickName, firstName, dateBirthday, balance FROM User");

        assertTrue("Resultset empty" , resultSet.next());
        assertEquals(resultSet.getString("lastName"), user.getLastName());
        assertEquals(resultSet.getString("firstName"), user.getFirstName());
        assertEquals(resultSet.getString("nickName"), user.getNickName());
        assertEquals(resultSet.getDate("dateBirthday"), user.getDateBirthday());
        assertEquals(resultSet.getInt("balance"), user.getBalance());
//        assertEquals(resultSet.getString("gender"), user.getGender());
    }
    public ResultSet executeQuery(String sql) throws SQLException {
        Connection connection = connectionPool.getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }
}