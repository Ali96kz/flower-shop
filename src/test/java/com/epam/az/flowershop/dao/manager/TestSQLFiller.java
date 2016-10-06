package com.epam.az.flowershop.dao.manager;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.manager.SQLFiller;
import com.epam.az.flower.shop.entity.*;
import com.epam.az.flower.shop.pool.ConnectionPool;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

public class TestSQLFiller {
    private static final int EXAMPLE_OBJECT_ID = 5;
    public static final int FLOWER_AVERAGE_HEIGHT = 54;
    public static final String FLOWER_NAME = "Catalina";
    public static final String ORIGIN_COUNTRY_NAME = "Kazakhstan";
    public static final String ORIGIN_PROVINCE_NAME = "Almaty";

    private static Origin origin = new Origin();
    private static Flower flower = new Flower();
    private static ConnectionPool connectionPool = new ConnectionPool();
    private static final String FLOWER_INSERT_SQL = "INSERT INTO Flower(name, averageHeight, visualParametersId, growingConditionId, flowerTypeId)values(?, ?, ?, ?, ?);";
    private static final String ORIGIN_INSERT_SQL = "INSERT INTO Origin(country,province)values(?, ?);";
    private SQLFiller sqlFiller = new SQLFiller();

    public static PreparedStatement createPrepareStatementForOrigin() throws SQLException {
        origin.setCountry(ORIGIN_COUNTRY_NAME);
        origin.setProvince(ORIGIN_PROVINCE_NAME);
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(ORIGIN_INSERT_SQL);
        return preparedStatement;
    }


    public static PreparedStatement createPrepareStatementForFlower() throws SQLException {
        FlowerType flowerType = new FlowerType();
        flower.setName(FLOWER_NAME);
        flower.setAverageHeight(FLOWER_AVERAGE_HEIGHT);
        VisualParameters visualParameters = new VisualParameters();
        GrowingCondition growingCondition = new GrowingCondition();

        flowerType.setId(EXAMPLE_OBJECT_ID);
        visualParameters.setId(EXAMPLE_OBJECT_ID);
        growingCondition.setId(EXAMPLE_OBJECT_ID);
        flower.setFlowerType(flowerType);
        flower.setGrowingCondition(growingCondition);
        flower.setVisualParameters(visualParameters);
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(FLOWER_INSERT_SQL);
        return preparedStatement;
    }

    @Test
    public void testFillPrepareStatementForComplexObject() throws DAOException, SQLException {
        PreparedStatement preparedStatement = createPrepareStatementForFlower();
        sqlFiller.fillPrepareStatement(preparedStatement, flower);
        String prepareSQL = ((com.mysql.cj.jdbc.PreparedStatement) preparedStatement).asSql();
        assertEquals("Create incorrect sql for insert", "INSERT INTO Flower(name, averageHeight, visualParametersId, growingConditionId," +
                " flowerTypeId)values('"+FLOWER_NAME+"', "+FLOWER_AVERAGE_HEIGHT+", 5, 5, 5);", prepareSQL);
    }
    @Test
    public void testFillPrepareStatementForPrimitiveObject() throws SQLException, DAOException {
        PreparedStatement preparedStatement = createPrepareStatementForOrigin();
        sqlFiller.fillPrepareStatement(preparedStatement, origin);
        String prepareSQL = ((com.mysql.cj.jdbc.PreparedStatement) preparedStatement).asSql();
        assertEquals("Create incorrect sql for insert", "INSERT INTO Origin(country,province)values('"+ORIGIN_COUNTRY_NAME+"', '"+ORIGIN_PROVINCE_NAME+"');", prepareSQL);

    }

}
