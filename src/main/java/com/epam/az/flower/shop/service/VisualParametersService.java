package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.VisualParametersDAO;
import com.epam.az.flower.shop.entity.VisualParameters;

import java.util.List;

public class VisualParametersService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private VisualParametersDAO visualParametersDAO;
    public List<VisualParameters> getAllVisualParameters(){
        return visualParametersDAO.getAll();
    }
    public VisualParametersService() throws ServiceException {
        try {
            visualParametersDAO = daoFactory.getDao(VisualParametersDAO.class);
        } catch (DAOException e) {
            throw new ServiceException("can't initialize visualParametersDAO", e);
        }
    }
    public VisualParameters  findById(int id) throws ServiceException {
        try {
            return visualParametersDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't find view by id ", e);
        }
    }
}
