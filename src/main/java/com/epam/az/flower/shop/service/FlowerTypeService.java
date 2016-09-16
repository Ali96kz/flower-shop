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
        try {
            daoFactory.startTransaction(flowerTypeDAO);
            List<FlowerType> flowerTypes = flowerTypeDAO.getAll();
            daoFactory.commitTransaction(flowerTypeDAO);
            return flowerTypes;
        } catch (DAOException e) {
            throw new ServiceException("", e);
        }
    }

    public FlowerType findById(int id) throws ServiceException {
        try {
            daoFactory.startTransaction(flowerTypeDAO);
            FlowerType flowerType = flowerTypeDAO.findById(id);
            daoFactory.commitTransaction(flowerTypeDAO);
            return flowerType;

        } catch (DAOException e) {
            try {
                daoFactory.rollBack(flowerTypeDAO);
            } catch (DAOException e1) {
                e1.printStackTrace();
            }
            throw new ServiceException("can't get flower type from dao", e);
        }
    }
}
