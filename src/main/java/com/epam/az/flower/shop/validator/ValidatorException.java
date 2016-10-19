package com.epam.az.flower.shop.validator;

public class ValidatorException extends Exception {
    public ValidatorException(String msg, Exception e) {
        super(msg, e);
    }
}
