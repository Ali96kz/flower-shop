package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.*;
import com.epam.az.flower.shop.entity.GrowingCondition;
import com.epam.az.flower.shop.entity.Temperature;
import com.epam.az.flower.shop.entity.WaterInWeek;

import java.util.List;

public class GrowingConditionService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    TemperatureService temperatureService;
    WaterInWeekService waterInWeekService;
    GrowingConditionDAO growingConditionDAO;
    public GrowingConditionService() throws ServiceException {
        try {
            temperatureService = new TemperatureService();
            waterInWeekService = new WaterInWeekService();
            growingConditionDAO = daoFactory.getDao(GrowingConditionDAO.class);
        } catch (DAOException e) {
            throw new ServiceException("", e);
        }
    }

    public List<GrowingCondition> getAllGrowingConditions() {
        return growingConditionDAO.getAll();
    }



    public GrowingCondition findById(Integer id) throws ServiceException {
        try {
            daoFactory.startTransaction(growingConditionDAO);
            GrowingCondition growingCondition = growingConditionDAO.findById(id);
            Temperature temperature = temperatureService.findById(growingCondition.getTemperature().getId());
            WaterInWeek waterInWeek = waterInWeekService.findById(growingCondition.getWaterInWeek().getId());
            growingCondition.setWaterInWeek(waterInWeek);
            growingCondition.setTemperature(temperature);
            daoFactory.commitTransaction(growingConditionDAO);
            return growingCondition;
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(growingConditionDAO);
            } catch (DAOException e1) {
                e1.printStackTrace();
            }
            throw new ServiceException("", e);
        }

    }

    public int add(GrowingCondition growingCondition) throws ServiceException {
        try {
            daoFactory.startTransaction(growingConditionDAO);
            int growingConditionId = growingConditionDAO.insert(growingCondition);
            daoFactory.commitTransaction(growingConditionDAO);
            return growingConditionId;
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(growingConditionDAO);
            } catch (DAOException e1) {
                e1.printStackTrace();
            }
            throw new ServiceException("can't add growing condition dao", e);
        }
    }
}
