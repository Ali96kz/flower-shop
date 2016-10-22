package com.epam.az.flower.shop.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidatorException extends Exception {
    private static Logger logger = LoggerFactory.getLogger(ValidatorException.class);

    public ValidatorException(String msg, Exception e) {
        super(msg, e);
        logger.error(msg, e);
    }
}
