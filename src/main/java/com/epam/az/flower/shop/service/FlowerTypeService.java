package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.FlowerTypeDAO;
import com.epam.az.flower.shop.entity.FlowerType;

import java.util.List;

public class FlowerTypeService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private FlowerTypeDAO flowerTypeDAO = daoFactory.getDao(FlowerTypeDAO.class);

    public List<FlowerType> getAllFlowerType(){
        return flowerTypeDAO.getAll();
    }

    public FlowerType findById(int id) throws ServiceException {
        try {
            return flowerTypeDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("can't get flower type from dao", e);
        }
    }
}
