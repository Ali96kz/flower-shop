package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.FlowerDAO;
import com.epam.az.flower.shop.dao.VisualParametersDAO;
import com.epam.az.flower.shop.entity.Flower;
import com.epam.az.flower.shop.entity.GrowingCondition;
import com.epam.az.flower.shop.entity.VisualParameters;

import java.util.List;

public class VisualParametersService {
    public List<VisualParameters> getAll() throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                VisualParametersDAO visualParametersDAO = daoFactory.createDAO(VisualParametersDAO.class);
                return visualParametersDAO.getAll();
            } catch (DAOException e) {
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public VisualParameters  findById(int id) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                VisualParametersDAO visualParametersDAO = daoFactory.createDAO(VisualParametersDAO.class);
                return visualParametersDAO.findById(id);
            } catch (DAOException e) {
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }
}
