package com.epam.az.flower.shop.util;

import com.sun.xml.internal.ws.util.UtilException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class UtilClassException extends IOException {
    private static Logger logger = LoggerFactory.getLogger(UtilException.class);

    public UtilClassException(String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }
}
