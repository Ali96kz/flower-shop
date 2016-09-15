package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.FlowerTypeDAO;
import com.epam.az.flower.shop.entity.FlowerType;

import java.util.List;

public class FlowerTypeService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private FlowerTypeDAO flowerTypeDAO;
    public FlowerTypeService() throws ServiceException {
        try {
            flowerTypeDAO = daoFactory.getDao(FlowerTypeDAO.class);
        } catch (DAOException e) {
            throw new ServiceException("can't initialize ", e);
        }
    }

    public List<FlowerType> getAllFlowerType() throws ServiceException {
            List<FlowerType> flowerTypes = flowerTypeDAO.getAll();
            return flowerTypes;
    }

    public FlowerType findById(int id) throws ServiceException {
        try {
            return flowerTypeDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("can't get flower type from dao", e);
        }
    }
}
