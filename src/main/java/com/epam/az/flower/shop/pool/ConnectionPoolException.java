package com.epam.az.flower.shop.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionPoolException extends RuntimeException {
    private static Logger logger = LoggerFactory.getLogger(ConnectionPoolException.class);

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
        logger.error(message,  cause);
    }

}

