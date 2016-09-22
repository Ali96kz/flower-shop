package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.dao.*;
import com.epam.az.flower.shop.entity.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TransactionService {
    public Transaction getTransactionByName(String name) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                TransactionDAO transactionDAO = daoFactory.createDAO(TransactionDAO.class);
                Transaction transaction = transactionDAO.getTransactionByName(name);
                return transaction;
            } catch (DAOException e) {
                daoFactory.rollBack();
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }
    public Transaction findById(Integer id) throws ServiceException {
        try (DAOFactory daoFactory = new DAOFactory()) {
            try {
                TransactionDAO transactionDAO = daoFactory.createDAO(TransactionDAO.class);
                Transaction transaction = transactionDAO.findById(id);
                return transaction;
            } catch (DAOException e) {
                daoFactory.rollBack();
                throw new ServiceException("Problem with dao factory", e);
            }
        } catch (Exception e) {
            throw new ServiceException("Can't find object by id", e);
        }
    }

}
