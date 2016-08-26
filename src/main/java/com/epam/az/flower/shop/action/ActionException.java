package com.epam.az.flower.shop.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionException extends Exception {
    private static Logger logger = LoggerFactory.getLogger(ActionException.class);

    public ActionException(String message,Exception e){
        logger.trace(message, e);
    }
}
