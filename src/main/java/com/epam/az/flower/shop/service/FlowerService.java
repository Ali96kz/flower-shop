package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.FlowerDAO;
import com.epam.az.flower.shop.entity.Flower;
import com.epam.az.flower.shop.entity.GrowingCondition;
import com.epam.az.flower.shop.entity.VisualParameters;

import java.util.List;

public class FlowerService {
    private VisualParametersService visualParametersService = new VisualParametersService();
    private GrowingConditionService growingConditionService = new GrowingConditionService();

    public Flower findById(int id) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                FlowerDAO flowerDAO = daoFactory.createDAO(FlowerDAO.class);
                Flower flower = flowerDAO.findById(id);
                VisualParameters visualParameters = visualParametersService.findById(flower.getVisualParameters().getId());
                GrowingCondition growingCondition = growingConditionService.findById(flower.getGrowingCondition().getId());
                flower.setGrowingCondition(growingCondition);
                flower.setVisualParameters(visualParameters);
                return flower;
            } catch (DAOException e) {
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public void update(Flower flower) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                FlowerDAO flowerDAO = daoFactory.createDAO(FlowerDAO.class);
                daoFactory.startTransaction();
                flowerDAO.update(flower);
                daoFactory.commitTransaction();
            } catch (DAOException e) {
                daoFactory.rollBack();
                throw new ServiceException("can't get flower dao from factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("can't initialize factory");
        }
    }

    public int insert(Flower flower) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                FlowerDAO flowerDAO = daoFactory.createDAO(FlowerDAO.class);
                daoFactory.startTransaction();
                int flowerId = flowerDAO.insert(flower);
                daoFactory.commitTransaction();
                return flowerId;
            } catch (DAOException e) {
                daoFactory.rollBack();
                throw new ServiceException("can't get flower dao from factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("can't initialize factory");
        }
    }
}
