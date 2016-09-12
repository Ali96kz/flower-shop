package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.*;
import com.epam.az.flower.shop.entity.GrowingCondition;
import com.epam.az.flower.shop.entity.Temperature;
import com.epam.az.flower.shop.entity.WaterInWeek;

import java.util.List;

public class GrowingConditionService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private TemperatureDAO temperatureDAO;
    private WaterInWeekDAO waterInWeekDAO;
    private GrowingConditionDAO growingConditionDAO;

    public GrowingConditionService() {
        try {
            temperatureDAO = daoFactory.getDao(TemperatureDAO.class);
            waterInWeekDAO = daoFactory.getDao(WaterInWeekDAO.class);
            growingConditionDAO = daoFactory.getDao(GrowingConditionDAO.class);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public List<GrowingCondition> getAllGrowingConditions() {
        return growingConditionDAO.getAll();
    }

    public List<Temperature> getAllTemperature() {
        return temperatureDAO.getAll();
    }


    public GrowingCondition findById(Integer id) {
        GrowingCondition growingCondition = null;
        WaterInWeek waterInWeek = null;
        Temperature temperature = null;
        try {
            growingCondition = growingConditionDAO.findById(id);
            temperature = temperatureDAO.findById(growingCondition.getTemperature().getId());
            waterInWeek = waterInWeekDAO.findById(growingCondition.getWaterInWeek().getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }

        growingCondition.setWaterInWeek(waterInWeek);
        growingCondition.setTemperature(temperature);
        return growingCondition;
    }

    public int add(GrowingCondition growingCondition) throws ServiceException {
        try {
            int growingConditionId = growingConditionDAO.insert(growingCondition);
            return growingConditionId;
        } catch (DAOException e) {
            throw new ServiceException("can't add growing condition dao", e);
        }
    }
}
