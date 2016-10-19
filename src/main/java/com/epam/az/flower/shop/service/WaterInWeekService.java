package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.WaterInWeekDAO;
import com.epam.az.flower.shop.entity.WaterInWeek;

public class WaterInWeekService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private WaterInWeekDAO waterInWeekDAO = daoFactory.getDao(WaterInWeekDAO.class);

    public WaterInWeek findById(int id) throws ServiceException {
        try {
            daoFactory.startOperation(waterInWeekDAO);
            WaterInWeek waterInWeek = waterInWeekDAO.findById(id);
            daoFactory.endOperation(waterInWeekDAO);
            return waterInWeek;
        } catch (DAOException e) {
            throw new ServiceException("can't find  by id", e);
        }
    }
}
