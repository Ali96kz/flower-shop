package com.epam.az.flower.shop.DAO;

import com.epam.az.flower.shop.entity.Flower;
import com.epam.az.flower.shop.entity.GrowingCondition;
import com.epam.az.flower.shop.entity.Temperature;
import org.junit.Test;

public class FlowerDAOTest {
    GrowingCondition growingCondition = new GrowingCondition();
    Temperature temperature = new Temperature();

    @Test
    public void insertTest() {
        Flower flower = new Flower();
        flower.setName("sad");

    }

    @Test
    public void updateTest() {

    }

    @Test
    public void findTest() {

    }


}
