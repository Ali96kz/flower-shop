package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.FlowerTypeDAO;
import com.epam.az.flower.shop.entity.FlowerType;

import java.util.List;

public class FlowerTypeService {
    public List<FlowerType> getAllFlowerType() throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                FlowerTypeDAO flowerTypeDAO = daoFactory.createDAO(FlowerTypeDAO.class);
                List<FlowerType> flowerTypes = flowerTypeDAO.getAll();
                return flowerTypes;
            } catch (DAOException e) {
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public FlowerType findById(int id) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                FlowerTypeDAO flowerTypeDAO = daoFactory.createDAO(FlowerTypeDAO.class);
                FlowerType flowerType = flowerTypeDAO.findById(id);
                return flowerType;
            } catch (DAOException e) {
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }

    }
}
