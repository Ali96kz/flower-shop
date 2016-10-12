package com.epam.az.flowershop.dao.manager;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.manager.SQLParser;
import com.epam.az.flower.shop.entity.Origin;
import com.epam.az.flower.shop.pool.ConnectionPool;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSqlParser {
    public static final int TEST_ORIGIN_ID = 1;
    public static final String SQL_FIND_ORIGIN_BY_ID = "SELECT Origin.id, Origin.country, Origin.province, Origin.deleteDay FROM Origin WHERE Origin.id =";
    private SQLParser sqlParser = new SQLParser();
    private ConnectionPool connectionPool = new ConnectionPool();

    @Test
    public void testParseEntityWithoutNestedEntity() throws SQLException, DAOException {
        Connection connection = connectionPool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_FIND_ORIGIN_BY_ID + " " + TEST_ORIGIN_ID);

        Origin origin;
        if(resultSet.next())
            origin = (Origin) sqlParser.parseResultSet(new Origin(), resultSet);

    }
}
