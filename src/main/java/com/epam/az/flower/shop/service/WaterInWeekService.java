package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.VisualParametersDAO;
import com.epam.az.flower.shop.dao.WaterInWeekDAO;
import com.epam.az.flower.shop.entity.WaterInWeek;

public class WaterInWeekService {

    public WaterInWeek findById(int id) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                WaterInWeekDAO waterInWeekDAO = daoFactory.createDAO(WaterInWeekDAO.class);
                WaterInWeek waterInWeek = waterInWeekDAO.findById(id);
                return waterInWeek;
            } catch (DAOException e) {
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }
}
