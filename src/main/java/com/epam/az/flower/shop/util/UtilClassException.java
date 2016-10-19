package com.epam.az.flower.shop.util;

import java.io.IOException;

public class UtilClassException extends IOException {
    public UtilClassException(String message) {
        super(message);
    }

    public UtilClassException(String message, Exception e) {
        super(message, e);
    }
}
