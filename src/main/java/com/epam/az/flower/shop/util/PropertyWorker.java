package com.epam.az.flower.shop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PropertyWorker {
    private static Logger logger = LoggerFactory.getLogger(PropertyWorker.class);

    public Properties readProperty(String fileName) throws UtilClassException {
        Properties properties = new Properties();
        try (InputStream in = PropertyWorker.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(in);
        } catch (IOException e) {
            throw new UtilClassException("Could not load property file", e);
        }
        return properties;
    }
}
