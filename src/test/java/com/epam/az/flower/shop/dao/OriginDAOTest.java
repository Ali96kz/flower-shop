package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.entity.Origin;
import com.epam.az.flower.shop.pool.ConnectionPool;
import com.epam.az.flower.shop.util.StringAdapter;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OriginDAOTest {
    private OriginDAO originDAO = new OriginDAO();
    private static Origin origin = new Origin();

    @BeforeClass
    public static void init() {
        origin.setId(545);
        origin.setProvince("asd");
        origin.setCountry("sads");
    }

    @Test
    public void testcreateSqlForPreparedStatement() throws SQLException, DAOException {
        StringBuilder sql = new StringBuilder();
        StringBuilder values = new StringBuilder();
        sql.append("INSERT INTO " + Origin.class.getSimpleName() + "(");
        originDAO.createSqlForPreparedStatement(sql, values, origin);
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql +" )values(" + values+")");
        originDAO.fillPrepareStatement(preparedStatement, origin);
        preparedStatement.execute();
    }

    @Test
    public void testBoolean() {
        boolean s = true;
        Object o = s;

        System.out.println(o.getClass().getSimpleName());
        System.out.println(o instanceof Boolean);
    }
}
