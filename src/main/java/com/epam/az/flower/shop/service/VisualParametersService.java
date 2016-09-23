package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.VisualParametersDAO;
import com.epam.az.flower.shop.entity.VisualParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VisualParametersService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private VisualParametersDAO visualParametersDAO;
    private static Logger logger = LoggerFactory.getLogger(VisualParametersService.class);

    public VisualParametersService() throws ServiceException {
        try {
            visualParametersDAO = daoFactory.getDao(VisualParametersDAO.class);
        } catch (DAOException e) {
            throw new ServiceException("can't initialize visualParametersDAO", e);
        }
    }
    public List<VisualParameters> getAll(){
        return visualParametersDAO.getAll();
    }
    public VisualParameters  findById(int id) throws ServiceException {
        try {

            daoFactory.startOperation(visualParametersDAO);
            logger.info("try to find visual parameters id = {}", id);
            VisualParameters visualParameters = visualParametersDAO.findById(id);
            daoFactory.endOperation(visualParametersDAO);
            return visualParameters;
        } catch (DAOException e) {
            throw new ServiceException("Can't find object by id ", e);
        }
    }
}
