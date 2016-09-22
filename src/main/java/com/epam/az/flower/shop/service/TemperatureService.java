package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.TemperatureDAO;
import com.epam.az.flower.shop.entity.Temperature;

import java.util.List;

public class TemperatureService {
    public Temperature findById(int id) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                TemperatureDAO temperatureDAO= daoFactory.createDAO(TemperatureDAO.class);
                temperatureDAO = daoFactory.createDAO(TemperatureDAO.class);
                return temperatureDAO.findById(id);
            } catch (DAOException e) {
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public List<Temperature> getAll() throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                TemperatureDAO temperatureDAO= daoFactory.createDAO(TemperatureDAO.class);
                List<Temperature> temperatures = temperatureDAO.getAll();
                return temperatures;
            } catch (DAOException e) {
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }
}
