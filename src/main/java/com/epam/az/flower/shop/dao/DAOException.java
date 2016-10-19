package com.epam.az.flower.shop.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAOException extends Exception {
    private static Logger logger = LoggerFactory.getLogger(DAOException.class);

    public DAOException(String msg, Exception e) {
        super(msg, e);
        logger.error(msg, e);
    }

}
