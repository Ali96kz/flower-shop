package com.epam.az.flower.shop.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilterException extends Exception {
    private static Logger logger = LoggerFactory.getLogger(FilterException.class);

    public FilterException(String msg, Exception e) {
        super(msg, e);
        logger.error(msg, e);
    }
}
