package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.GrowingConditionDAO;
import com.epam.az.flower.shop.dao.TemperatureDAO;
import com.epam.az.flower.shop.dao.WaterInWeekDAO;
import com.epam.az.flower.shop.entity.GrowingCondition;
import com.epam.az.flower.shop.entity.Temperature;
import com.epam.az.flower.shop.entity.WaterInWeek;

import java.util.List;

public class GrowingConditionService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private TemperatureDAO temperatureDAO = daoFactory.getDao(TemperatureDAO.class);
    private WaterInWeekDAO waterInWeekDAO = daoFactory.getDao(WaterInWeekDAO.class);
    private GrowingConditionDAO growingConditionDAO = daoFactory.getDao(GrowingConditionDAO.class);


    public List<GrowingCondition> getAllGrowingConditions() {
        return growingConditionDAO.getAll();
    }
    public List<Temperature> getAllTemperature(){
        return temperatureDAO.getAll();
    }

    public List<WaterInWeek> getAllWaterInWeek(){
        return waterInWeekDAO.getAll();
    }

    public GrowingCondition findById(Integer id) {
        GrowingCondition growingCondition = growingConditionDAO.findById(id);
        Temperature temperature = temperatureDAO.findById(growingCondition.getTemperature().getId());
        WaterInWeek waterInWeek = waterInWeekDAO.findById(growingCondition.getWaterInWeek().getId());

        growingCondition.setWaterInWeek(waterInWeek);
        growingCondition.setTemperature(temperature);
        return growingCondition;
    }

    public int  add(GrowingCondition growingCondition) {
        return  growingConditionDAO.insert(growingCondition);
    }
}
