package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.TemperatureDAO;
import com.epam.az.flower.shop.entity.Temperature;

import java.util.List;

public class TemperatureService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private TemperatureDAO temperatureDAO ;

    public TemperatureService() throws ServiceException {
        try {
            temperatureDAO = daoFactory.getDao(TemperatureDAO.class);
        } catch (DAOException e) {
            throw new ServiceException("can't initialize dao", e);
        }
    }
    public Temperature findById(int id) throws ServiceException {
        try {
            daoFactory.startOperation(temperatureDAO);
            Temperature temperature = temperatureDAO.findById(id);
            daoFactory.endOperation(temperatureDAO);
            return temperature;

        } catch (DAOException e) {
            throw new ServiceException("can't find temperature by id", e);
        }
    }

    public List<Temperature> getAll() throws ServiceException {
        try {
            daoFactory.startTransaction(temperatureDAO);
            List<Temperature> temperatures = temperatureDAO.getAll();
            daoFactory.commitTransaction(temperatureDAO);
            return temperatures;
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(temperatureDAO);
            } catch (DAOException e1) {
                e1.printStackTrace();
            }
            throw new ServiceException("", e);
        }
    }
}
