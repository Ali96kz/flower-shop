package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.WaterInWeekDAO;
import com.epam.az.flower.shop.entity.WaterInWeek;

public class WaterInWeekService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private WaterInWeekDAO waterInWeekDAO ;

    public WaterInWeekService() throws ServiceException {
        try {
            waterInWeekDAO = daoFactory.getDao(WaterInWeekDAO.class);
        } catch (DAOException e) {
            throw new ServiceException("can't initialize dao class", e);
        }
    }


    public WaterInWeek findById(int id) throws ServiceException {
        try {
            daoFactory.startTransaction(waterInWeekDAO);
            WaterInWeek waterInWeek = waterInWeekDAO.findById(id);
            daoFactory.commitTransaction(waterInWeekDAO);
            return waterInWeek;
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(waterInWeekDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't roll back transaction", e);
            }
            throw new ServiceException("can't find  by id", e);
        }
    }
}
