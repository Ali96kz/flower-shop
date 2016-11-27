package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.DAOFactory;
import com.epam.az.flower.shop.dao.OriginDAO;
import com.epam.az.flower.shop.entity.Origin;
import com.epam.az.flower.shop.entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OriginService {
    private static Logger logger = LoggerFactory.getLogger(OrderService.class);
    private static final Class<OriginDAO> ORIGIN_DAO_CLASS = OriginDAO.class;
    private ProxyService proxyService = new ProxyService(ORIGIN_DAO_CLASS);
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private OriginDAO originDAO;

    public void initDAO() throws ServiceException {
        try {
            originDAO = daoFactory.getDao(OriginDAO.class);
        } catch (DAOException e) {
            throw new ServiceException("", e);
        }
    }

    public List<Origin> getAll() throws ServiceException {
        List<Origin> origins = proxyService.getAll();
        return origins;
    }

    public Origin findById(int id) throws ServiceException {
        Origin origin = (Origin) proxyService.findById(id);
        return origin;
    }

    public void executeProcedure(String country, String province) throws ServiceException {
        try {
            initDAO();
            daoFactory.startOperation(originDAO);
            originDAO.executeProcedure(country, province);
        } catch (DAOException e) {
            logger.error("can't get transaction by name", e);
            throw new ServiceException("can't get transaction by name", e);
        } finally {
            daoFactory.endOperation(originDAO);
        }
    }

    public int countAddMoney(int userId, int transactionId) throws ServiceException {
        try {
            initDAO();
            daoFactory.startOperation(originDAO);
            int sum = originDAO.countAddMoney(userId, transactionId);
            return sum;
        } catch (DAOException e) {
            logger.error("can't get transaction by name", e);
            throw new ServiceException("can't get transaction by name", e);
        } finally {
            daoFactory.endOperation(originDAO);
        }
    }
}
