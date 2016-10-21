package com.epam.az.flowershop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.FlowerDAO;
import com.epam.az.flower.shop.dao.OriginDAO;
import com.epam.az.flower.shop.entity.Flower;
import com.epam.az.flower.shop.entity.Origin;
import com.epam.az.flower.shop.pool.ConnectionPool;
import com.epam.az.flower.shop.service.ProxyService;
import com.epam.az.flower.shop.service.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProxyServiceTest {
    public static final int TEST_FLOWER_ID = 2;
    public static final int TEST_ORIGIN_ID = 5;
    private ProxyService flowerProxyService = new ProxyService(FlowerDAO.class);
    private ProxyService originProxyService = new ProxyService(OriginDAO.class);
    private FlowerDAO flowerDAO ;
    private ConnectionPool connectionPool = new ConnectionPool();
    private OriginDAO originDAO;
    @Before
    public void init() throws SQLException {
        flowerDAO = new FlowerDAO();
        originDAO = new OriginDAO();
        flowerDAO.setConnection(connectionPool.getConnection());
        originDAO.setConnection(connectionPool.getConnection());
    }

    @After
    public void finallize() throws SQLException {
        flowerDAO.getConnection().close();
        originDAO.getConnection().close();
    }

    @Test
    public void testFindById() throws ServiceException, DAOException, SQLException {
        Flower flower = flowerDAO.findById(TEST_FLOWER_ID);
        Flower testGetFlower = (Flower) flowerProxyService.findById(TEST_FLOWER_ID);
        assertEquals(flower.getId(), testGetFlower.getId());
        assertEquals(flower.getName(), testGetFlower.getName());
    }

    @Test
    public void testInsert() throws ServiceException, DAOException, SQLException {
        OriginDAO originDAO = new OriginDAO();
        originDAO.setConnection(connectionPool.getConnection());
        Origin origin = new Origin();
        origin.setCountry("Kazakhstan");
        origin.setProvince("Dolinka");

        flowerProxyService.insert(origin);
        List<Origin> origins = originDAO.getAll();
        Origin insertedOrigin = origins.get(origins.size() - 1);
        assertEquals(origin.getCountry(), insertedOrigin.getCountry());
        assertEquals(origin.getProvince(), insertedOrigin.getProvince());
    }

    @Test
    public void testUpdate() throws ServiceException, DAOException {
        Origin origin = new Origin();
        origin.setId(TEST_ORIGIN_ID);
        origin.setCountry("Russia");
        origin.setProvince("Saran");
        originProxyService.update(origin);

        origin = originDAO.findById(TEST_ORIGIN_ID);
        assertEquals("Russia", origin.getCountry());
        assertEquals("Saran", origin.getProvince());

    }

    @Test
    public void deleteTest() throws ServiceException, DAOException {
        originProxyService.delete(TEST_ORIGIN_ID);
        Origin origin = originDAO.findById(TEST_ORIGIN_ID);
        assertNotNull(origin.getDeleteDay());
    }
}