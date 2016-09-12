package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.TemperatureDAO;
import com.epam.az.flower.shop.entity.Temperature;

public class TemperatureService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private TemperatureDAO temperatureDAO ;

    public TemperatureService() throws DAOException {
        temperatureDAO = daoFactory.getDao(TemperatureDAO.class);
    }
    public Temperature findById(int id) throws ServiceException {
        try {

            return temperatureDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("can't find temperature by id", e);
        }
    }
}
