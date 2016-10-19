package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.FlowerDAO;
import com.epam.az.flower.shop.entity.Flower;
import com.epam.az.flower.shop.entity.GrowingCondition;
import com.epam.az.flower.shop.entity.VisualParameters;

public class FlowerService {
    public static final Class<FlowerDAO> daoClass = FlowerDAO.class;
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private FlowerDAO flowerDAO;
    private VisualParametersService visualParametersService = new VisualParametersService();
    private GrowingConditionService growingConditionService = new GrowingConditionService();


    public Flower findById(int id) throws ServiceException {
        Flower flower;
        try {
            flowerDAO = daoFactory.getDao(daoClass);
            daoFactory.startOperation(flowerDAO);
            flower = flowerDAO.findById(id);
            VisualParameters visualParameters = visualParametersService.findById(flower.getVisualParameters().getId());
            GrowingCondition growingCondition = growingConditionService.findById(flower.getGrowingCondition().getId());
            flower.setGrowingCondition(growingCondition);
            flower.setVisualParameters(visualParameters);
            daoFactory.endOperation(flowerDAO);
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(flowerDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't rollback transaction", e);
            }
            throw new ServiceException("Can't find object by id", e);
        }
        return flower;
    }

    public void update(Flower flower) throws ServiceException {
        try {
            daoFactory.startTransaction(flowerDAO);

            flowerDAO.update(flower);

            daoFactory.commitTransaction(flowerDAO);
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(flowerDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't rollback transaction", e);
            }
            throw new ServiceException("can't get flower dao from factory", e);
        }

    }

    public int insert(Flower flower) throws ServiceException {
        try {
            flowerDAO = daoFactory.getDao(FlowerDAO.class);

            daoFactory.startTransaction(flowerDAO);
            int flowerId = flowerDAO.insert(flower);

            daoFactory.commitTransaction(flowerDAO);
            return flowerId;
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(flowerDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't roll back transaction", e);
            }
            throw new ServiceException("can't get flower dao from factory", e);
        }
    }
}
