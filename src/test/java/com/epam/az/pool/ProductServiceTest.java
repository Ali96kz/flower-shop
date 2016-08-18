package com.epam.az.pool;

import com.epam.az.pool.DAO.AbstractDAO;
import com.epam.az.pool.DAO.UnityDAO;
import com.epam.az.pool.entity.Origin;
import com.epam.az.pool.entity.SyntheticFlower;
import com.epam.az.pool.service.ProductService;
import org.junit.Test;

public class ProductServiceTest {
    @Test
    public void test() {
        AbstractDAO abstractDAO = new UnityDAO(Origin.class);
        Origin origin = new Origin();
        origin.setId(520);

        SyntheticFlower syntheticFlower = new SyntheticFlower();
        syntheticFlower.setType("syntheticFlower");
        syntheticFlower.setName("BAS");
        syntheticFlower.setMaterial("asdsadasdasd");
        syntheticFlower.setDescription("asd");
        syntheticFlower.setOrigin(origin);

        ProductService<SyntheticFlower> productService = new ProductService<>();
        productService.update(syntheticFlower);
    }
}
