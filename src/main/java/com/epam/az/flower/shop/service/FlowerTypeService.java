package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.FlowerTypeDAO;
import com.epam.az.flower.shop.entity.FlowerType;

import java.util.List;

public class FlowerTypeService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private FlowerTypeDAO flowerTypeDAO;

    public void init() throws ServiceException {
        try {
            flowerTypeDAO = daoFactory.getDao(FlowerTypeDAO.class);
        } catch (DAOException e) {
            throw new ServiceException("Can't get dao from factory", e);
        }

    }

    public List<FlowerType> getAllFlowerType() throws ServiceException {
        try {
            if (flowerTypeDAO == null) {
                init();
            }
            daoFactory.startTransaction(flowerTypeDAO);
            List<FlowerType> flowerTypes = flowerTypeDAO.getAll();
            daoFactory.commitTransaction(flowerTypeDAO);
            return flowerTypes;
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(flowerTypeDAO);
            } catch (DAOException e1) {
                e1.printStackTrace();
            }
            throw new ServiceException("", e);
        }
    }

    public FlowerType findById(int id) throws ServiceException {
        try {
            return flowerTypeDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("can't get flower type from dao", e);
        }
    }
}
