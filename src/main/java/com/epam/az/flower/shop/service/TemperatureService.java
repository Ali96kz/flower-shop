package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.TemperatureDAO;
import com.epam.az.flower.shop.entity.Temperature;

import java.util.List;

public class TemperatureService {
    public static final Class<TemperatureDAO> TEMPERATURE_DAO_CLASS = TemperatureDAO.class;
    private ProxyService proxyService = new ProxyService(TEMPERATURE_DAO_CLASS);

    public Temperature findById(int id) throws ServiceException {
        Temperature temperature = (Temperature) proxyService.findById(id);
        return temperature;
    }

    public List<Temperature> getAll() throws ServiceException {
        List<Temperature> temperatures = proxyService.getAll();
        return temperatures;
    }
}
