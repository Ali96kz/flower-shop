package com.epam.az.flowershop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.FlowerDAO;
import com.epam.az.flower.shop.dao.OriginDAO;
import com.epam.az.flower.shop.entity.Flower;
import com.epam.az.flower.shop.entity.Origin;
import com.epam.az.flower.shop.pool.ConnectionPool;
import com.epam.az.flower.shop.service.ProxyService;
import com.epam.az.flower.shop.service.ServiceException;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProxyServiceTest {
    public static final int TEST_FLOWER_ID = 2;
    private ProxyService proxyService = new ProxyService();
    private FlowerDAO flowerDAO = new FlowerDAO();
    private ConnectionPool connectionPool = new ConnectionPool();

    @Before
    public void init() throws SQLException {
        flowerDAO = new FlowerDAO();
        flowerDAO.setConnection(connectionPool.getConnection());
    }

    @After
    public void finallize() throws SQLException {
        flowerDAO.getConnection().close();
    }

    @Test
    public void testFindById() throws ServiceException, DAOException, SQLException {
        Flower flower = flowerDAO.findById(TEST_FLOWER_ID);
        Flower testGetFlower = (Flower) proxyService.findById(FlowerDAO.class, TEST_FLOWER_ID);
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

        proxyService.insert(OriginDAO.class, origin);
        List<Origin> origins = originDAO.getAll();
        Origin insertedOrigin = origins.get(origins.size() - 1);
        assertEquals(origin.getCountry(), insertedOrigin.getCountry());
        assertEquals(origin.getProvince(), insertedOrigin.getProvince());
    }

}
