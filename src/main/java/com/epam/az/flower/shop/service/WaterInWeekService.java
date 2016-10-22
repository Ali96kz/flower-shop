package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.WaterInWeekDAO;
import com.epam.az.flower.shop.entity.WaterInWeek;

public class WaterInWeekService {
    private static final Class<WaterInWeekDAO> WATER_IN_WEEK_DAO_CLASS = WaterInWeekDAO.class;
    private ProxyService proxyService = new ProxyService(WATER_IN_WEEK_DAO_CLASS);

    public WaterInWeek findById(int id) throws ServiceException {
        WaterInWeek waterInWeek = (WaterInWeek) proxyService.findById(id);
        return waterInWeek;
    }
}
