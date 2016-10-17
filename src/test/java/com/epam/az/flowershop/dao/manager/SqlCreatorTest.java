package com.epam.az.flowershop.dao.manager;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.manager.PrepareSQLCreator;
import com.epam.az.flower.shop.entity.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SqlCreatorTest {

    private static final int EXAMPLE_OBJECT_ID = 5;
    private PrepareSQLCreator prepareSqlCreator = new PrepareSQLCreator();
    private static final Class PRIMITIVE_OBJECT_CLASS = Origin.class;
    private static final Class CLASS_OF_OBJECT_WITH_NESTED_OBJECT = Flower.class;

    @Test
    public void testGetAll() {
        String sql = prepareSqlCreator.createSqlForGetAll(PRIMITIVE_OBJECT_CLASS);
        assertEquals("Create incorrect sql for get all method", "SELECT Origin.id, Origin.deleteDay, " +
                "Origin.country, Origin.province FROM Origin;", sql);
    }

    @Test
    public void testFindById() {
        String sql = prepareSqlCreator.createSqlForFindById(PRIMITIVE_OBJECT_CLASS, EXAMPLE_OBJECT_ID);
        assertEquals("Create incorrect find by id", "SELECT Origin.id, Origin.deleteDay, Origin.country, Origin.province " +
                "FROM Origin where Origin.id = 5;", sql);
    }

    @Test
    public void testDeleteSQL() {
        String sql = prepareSqlCreator.createSQLForDelete(EXAMPLE_OBJECT_ID, PRIMITIVE_OBJECT_CLASS);
        assertEquals("Create incorrect sql for delete", "UPDATE Origin set deleteDAY = ? where id = 5;", sql);
    }

    @Test
    public void testInsertSQL() throws DAOException {
        String sql = prepareSqlCreator.createInsertSQL(PRIMITIVE_OBJECT_CLASS);
        assertEquals("Create incorrect sql for delete", "INSERT INTO Origin(country, province)values(?, ?);", sql);
    }

    @Test
    public void testUpdateSQL() throws DAOException {
        Origin origin = new Origin();
        origin.setId(EXAMPLE_OBJECT_ID);
        origin.setCountry("Kazakhstan");
        origin.setProvince("Astana");
        String sql = prepareSqlCreator.createSQLForUpdate(origin);
        assertEquals("Create incorrect sql for update", "UPDATE Origin SET country =  ?, province =  ? where id = 5;", sql);
    }

    /**
     * Complex object means object has a nested object.
     */

    @Test
    public void testGetAllForComplexObject() {
        String sql = prepareSqlCreator.createSqlForGetAll(CLASS_OF_OBJECT_WITH_NESTED_OBJECT);
        assertEquals("Create incorrect sql for get all method", "SELECT Flower.id, Flower.deleteDay, Flower.name, " +
                "Flower.averageHeight, visualParametersId, growingConditionId, flowerTypeId FROM Flower;", sql);
    }
    @Test
    public void testInsertForComplexObject() throws DAOException {
        String sql = prepareSqlCreator.createInsertSQL(CLASS_OF_OBJECT_WITH_NESTED_OBJECT);
        assertEquals("Create incorrect sql for insert", "INSERT INTO Flower(name, averageHeight, visualParametersId, growingConditionId," +
                " flowerTypeId)values(?, ?, ?, ?, ?);", sql);
    }

    @Test
    public void testFindByIdForComplexObject() {
        String sql = prepareSqlCreator.createSqlForFindById(CLASS_OF_OBJECT_WITH_NESTED_OBJECT, EXAMPLE_OBJECT_ID);
        assertEquals("Create incorrect find by id", "SELECT Flower.id, Flower.deleteDay, " +
                "Flower.name, Flower.averageHeight, visualParametersId, growingConditionId, flowerTypeId FROM Flower where Flower.id = 5;", sql);
    }

    @Test
    public void testDeleteForComplexObject() {
        String sql = prepareSqlCreator.createSQLForDelete(EXAMPLE_OBJECT_ID, CLASS_OF_OBJECT_WITH_NESTED_OBJECT);
        assertEquals("Create incorrect sql for delete", "UPDATE Flower set deleteDAY = ? where id = 5;", sql);
    }

    @Test
    public void testUpdateForComplexObject() throws DAOException {
        Flower flower = initializeFlower();
        String sql = prepareSqlCreator.createSQLForUpdate(flower);
        assertEquals("Create incorrect sql for update", "UPDATE Flower SET nameId = ?," +
                "averageHeight = ?, visualParametersId = ?,growingConditionId = ?,flowerTypeId = ? where id = 5;", sql);
    }



    public Flower initializeFlower() {
        Flower flower = new Flower();
        flower.setId(EXAMPLE_OBJECT_ID);
        return flower;
    }
}
