package com.epam.az.flower.shop.util;

import java.io.IOException;

public class PropertyWorkerException extends IOException{
    public PropertyWorkerException(String message) {
        super(message);
    }

    public PropertyWorkerException(String message, Exception e) {
        super(message, e);
    }
}
