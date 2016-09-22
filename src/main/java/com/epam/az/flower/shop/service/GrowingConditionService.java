package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.*;
import com.epam.az.flower.shop.entity.GrowingCondition;
import com.epam.az.flower.shop.entity.Temperature;
import com.epam.az.flower.shop.entity.WaterInWeek;

import java.util.List;

public class GrowingConditionService {
    private  TemperatureService temperatureService = new TemperatureService();
    private WaterInWeekService waterInWeekService = new WaterInWeekService();

    public List<GrowingCondition> getAllGrowingConditions() throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                GrowingConditionDAO growingConditionDAO = daoFactory.createDAO(GrowingConditionDAO.class);
                List<GrowingCondition> growingConditions = growingConditionDAO.getAll();
                return growingConditions;
            } catch (DAOException e) {
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public GrowingCondition findById(Integer id) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                GrowingConditionDAO growingConditionDAO = daoFactory.createDAO(FlowerTypeDAO.class);
                GrowingCondition growingCondition = growingConditionDAO.findById(id);
                Temperature temperature = temperatureService.findById(growingCondition.getTemperature().getId());
                WaterInWeek waterInWeek = waterInWeekService.findById(growingCondition.getWaterInWeek().getId());
                growingCondition.setWaterInWeek(waterInWeek);
                growingCondition.setTemperature(temperature);
                return growingCondition;
            } catch (DAOException e) {
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }
}
