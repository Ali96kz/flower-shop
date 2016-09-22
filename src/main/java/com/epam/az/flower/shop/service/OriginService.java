package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.OriginDAO;
import com.epam.az.flower.shop.entity.Origin;

import java.util.List;

public class OriginService {

    public List<Origin> getAll() throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            OriginDAO originDAO = daoFactory.createDAO(OriginDAO.class);
            List<Origin> origins = originDAO.getAll();
            return origins;
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

    public Origin findById(int id) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            OriginDAO originDAO = daoFactory.createDAO(OriginDAO.class);
            Origin origin = originDAO.findById(id);
            return origin;
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }
}
