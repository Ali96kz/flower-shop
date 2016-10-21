package com.epam.az.flower.shop.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    public Properties readProperty(String fileName) throws UtilClassException {
        Properties properties = new Properties();
        try (InputStream in = PropertyManager.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(in);
        } catch (IOException e) {
            throw new UtilClassException("Could not load property file", e);
        }
        return properties;
    }
}
