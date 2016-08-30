package com.epam.az.flower.shop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PropertyWorker {
    private static Logger logger = LoggerFactory.getLogger(PropertyWorker.class);

    public Properties readProperty(String path){
        InputStream input ;
        Properties properties = new Properties();
        try {
            input = new FileInputStream("./src/main/resources/actionClass.properties");
            properties.load(input);
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
