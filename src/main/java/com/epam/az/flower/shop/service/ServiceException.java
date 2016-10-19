package com.epam.az.flower.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceException extends Exception {
    private static Logger logger = LoggerFactory.getLogger(ServiceException.class);

    public ServiceException(String msg, Exception e) {
        super(msg, e);
        logger.error(msg, e);
    }
}
