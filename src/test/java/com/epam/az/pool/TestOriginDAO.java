package com.epam.az.pool;

import com.epam.az.pool.DAO.AbstractDAO;
import com.epam.az.pool.DAO.UnityDAO;
import com.epam.az.pool.entity.Origin;
import com.epam.az.pool.entity.Temperature;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestOriginDAO {
    static AbstractDAO abstractDAO = new UnityDAO(Temperature.class);
    static Temperature origin = new Temperature();

    @BeforeClass
    public static void initOrigin() {
        origin.setMin(15);
        origin.setMax(22);
    }

    @Test
    public void testInsert() {
        abstractDAO.insert(origin);
    }

    @Test
    public void testDelete() {
        abstractDAO.delete(origin);
        assertEquals("", origin.equals(origin), true);
    }

    @Test
    public void testfindById() {
        Origin origin1 = (Origin) abstractDAO.findById(10);
        assertEquals("", origin.equals(origin1), true);
    }

    @Test
    public void testGetAll() {
        List<Origin> list = abstractDAO.getAll();
        for (Origin origin1 : list) {
            assertNotNull("Empty origin", origin1.getId());
        }
        assertEquals("Wrong list size", list.size(), 3);
    }

    @Test
    public void testUpdate() {
        abstractDAO.update(origin);
    }
}
