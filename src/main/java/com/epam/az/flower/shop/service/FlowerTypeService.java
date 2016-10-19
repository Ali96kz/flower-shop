package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.FlowerTypeDAO;
import com.epam.az.flower.shop.entity.FlowerType;

import java.util.List;

public class FlowerTypeService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private FlowerTypeDAO flowerTypeDAO = daoFactory.getDao(FlowerTypeDAO.class);


    public List<FlowerType> getAllFlowerType() throws ServiceException {
        try {
            daoFactory.startOperation(flowerTypeDAO);
            List<FlowerType> flowerTypes = flowerTypeDAO.getAll();
            return flowerTypes;
        } catch (DAOException e) {
            throw new ServiceException("", e);
        } finally {
            daoFactory.endOperation(flowerTypeDAO);
        }
    }

    public FlowerType findById(int id) throws ServiceException {
        try {
            daoFactory.startOperation(flowerTypeDAO);
            FlowerType flowerType = flowerTypeDAO.findById(id);
            return flowerType;
        } catch (DAOException e) {
            throw new ServiceException("can't get flower type from dao", e);
        } finally {
            daoFactory.endOperation(flowerTypeDAO);
        }
    }
}
