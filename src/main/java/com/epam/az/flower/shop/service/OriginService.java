package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.OriginDAO;
import com.epam.az.flower.shop.entity.Origin;

import java.util.List;

public class OriginService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private OriginDAO originDAO ;

    public OriginService() throws ServiceException {
        try {
            originDAO = daoFactory.getDao(OriginDAO.class);
        } catch (DAOException e) {
            throw new ServiceException("can't initialize dao class", e);
        }
    }

    public List<Origin> getAll() throws ServiceException {
        try {
            daoFactory.startTransaction(originDAO);
            List<Origin> origins = originDAO.getAll();
            daoFactory.commitTransaction(originDAO);
            return origins;
        } catch (DAOException e) {
            try {
                daoFactory.rollBack(originDAO);
            } catch (DAOException e1) {
                throw new ServiceException("can't roll back transaction", e);
            }
            throw new ServiceException("can't execute ", e);
        }
    }

    public Origin findById(int id) throws ServiceException {
        try {
            return originDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("can't find origin by id", e);
        }
    }
}
