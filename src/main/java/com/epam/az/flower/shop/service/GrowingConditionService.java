package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.GrowingConditionDAO;
import com.epam.az.flower.shop.entity.GrowingCondition;
import com.epam.az.flower.shop.entity.Temperature;
import com.epam.az.flower.shop.entity.WaterInWeek;

import java.util.List;

public class GrowingConditionService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private TemperatureService temperatureService = new TemperatureService();
    ;
    private WaterInWeekService waterInWeekService = new WaterInWeekService();
    private GrowingConditionDAO growingConditionDAO = daoFactory.getDao(GrowingConditionDAO.class);

    public List<GrowingCondition> getAllGrowingConditions() throws ServiceException {
        try {
            daoFactory.startOperation(growingConditionDAO);
            List<GrowingCondition> growingConditions = growingConditionDAO.getAll();
            return growingConditions;
        } catch (DAOException e) {
            throw new ServiceException("can't end operation", e);
        } finally {
            daoFactory.endOperation(growingConditionDAO);
        }
    }

    public GrowingCondition findById(Integer id) throws ServiceException {
        try {
            daoFactory.startOperation(growingConditionDAO);
            GrowingCondition growingCondition = growingConditionDAO.findById(id);
            fillGrowingCondition(growingCondition);
            return growingCondition;
        } catch (DAOException e) {
            throw new ServiceException("", e);
        } finally {
            daoFactory.endOperation(growingConditionDAO);
        }
    }

    public void fillGrowingCondition(GrowingCondition growingCondition) throws ServiceException {
        Temperature temperature;
        try {
            temperature = temperatureService.findById(growingCondition.getTemperature().getId());
            WaterInWeek waterInWeek = waterInWeekService.findById(growingCondition.getWaterInWeek().getId());
            growingCondition.setWaterInWeek(waterInWeek);
            growingCondition.setTemperature(temperature);
        } catch (ServiceException e) {
            throw new ServiceException("can't fill growing condition", e);
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
