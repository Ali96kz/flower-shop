package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.entity.GrowingCondition;
import com.epam.az.flower.shop.entity.Temperature;
import com.epam.az.flower.shop.entity.WaterInWeek;
import com.epam.az.flower.shop.pool.ConnectionPool;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class GrowingConditionDAOTest {
    private GrowingConditionDAO growingConditionDAO = new GrowingConditionDAO();
    private static GrowingCondition growingCondition = new GrowingCondition();

    @BeforeClass
    public static void init() {
        Temperature temperature = new Temperature();
        WaterInWeek waterInWeek = new WaterInWeek();
        temperature.setId(3);
        waterInWeek.setId(3);

        growingCondition.setId(19);
        growingCondition.setName("typically");
        growingCondition.setLovelight(true);
        growingCondition.setTemperature(temperature);
        growingCondition.setWaterInWeek(waterInWeek);
    }

    @Test
    public void testInsert() throws SQLException, DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        growingConditionDAO.setConnection(connection);
        growingConditionDAO.insert(growingCondition);
    }
    @Test
    public void testUpdateStatement() throws DAOException, SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        growingConditionDAO.setConnection(connection);
        growingConditionDAO.update(growingCondition);
    }
}
