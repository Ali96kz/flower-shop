package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.OriginDAO;
import com.epam.az.flower.shop.entity.Origin;

import java.util.List;

public class OriginService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private OriginDAO originDAO = daoFactory.getDao(OriginDAO.class);
    public List<Origin> getAllOrigin(){
        return originDAO.getAll();
    }

    public Origin findById(int id) throws ServiceException {
        try {
            return originDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("can;t find origin by id", e);
        }
    }
}
