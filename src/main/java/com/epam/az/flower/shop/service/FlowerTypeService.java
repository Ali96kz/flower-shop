package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.FlowerTypeDAO;
import com.epam.az.flower.shop.entity.FlowerType;

import java.util.List;

public class FlowerTypeService {
    private static final Class<FlowerTypeDAO> FLOWER_TYPE_DAO_CLASS = FlowerTypeDAO.class;
    private ProxyService proxyService = new ProxyService(FLOWER_TYPE_DAO_CLASS);

    public List<FlowerType> getAllFlowerType() throws ServiceException {
        List<FlowerType> flowerTypes = proxyService.getAll();
        return flowerTypes;
    }

    public FlowerType findById(int id) throws ServiceException {
        FlowerType flowerType = (FlowerType) proxyService.findById(id);
        return flowerType;
    }
}
