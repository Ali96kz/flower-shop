package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.GrowingConditionDAO;
import com.epam.az.flower.shop.entity.GrowingCondition;
import com.epam.az.flower.shop.entity.Temperature;
import com.epam.az.flower.shop.entity.WaterInWeek;

import java.util.List;

public class GrowingConditionService {
    private static final Class<GrowingConditionDAO> GROWING_CONDITION_DAO_CLASS = GrowingConditionDAO.class;
    private TemperatureService temperatureService = new TemperatureService();
    private WaterInWeekService waterInWeekService = new WaterInWeekService();
    private ProxyService proxyService = new ProxyService(GROWING_CONDITION_DAO_CLASS);

    public List<GrowingCondition> getAllGrowingConditions() throws ServiceException {
        List<GrowingCondition> growingConditions = proxyService.getAll();
        return growingConditions;
    }

    public GrowingCondition findById(Integer id) throws ServiceException {
        GrowingCondition growingCondition = (GrowingCondition) proxyService.findById(id);
        fillGrowingCondition(growingCondition);
        return growingCondition;
    }

    public void fillGrowingCondition(GrowingCondition growingCondition) throws ServiceException {
        Temperature temperature = temperatureService.findById(growingCondition.getTemperature().getId());
        WaterInWeek waterInWeek = waterInWeekService.findById(growingCondition.getWaterInWeek().getId());
        growingCondition.setWaterInWeek(waterInWeek);
        growingCondition.setTemperature(temperature);
    }

    public int add(GrowingCondition growingCondition) throws ServiceException {
        int growingConditionId = proxyService.insert(growingCondition);
        return growingConditionId;
    }
}
