package com.epam.az.flower.shop.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionException extends Exception {

    public ActionException(String message,Exception e){
        super(message, e);
    }
}
