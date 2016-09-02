package com.epam.az.flower.shop.dao;

import java.util.HashMap;
import java.util.Map;

public class DAOFactory {
    private Map<Class, AbstractDAO> daomaps = new HashMap<>();
    private static DAOFactory daoFactory = new DAOFactory();
    private DAOFactory() {

    }
    public static DAOFactory getInstance(){
        return daoFactory;
    }
    public <E> E getDao(Class aClass) {
        if (daomaps.get(aClass) == null) {
            try {
                daomaps.put(aClass, (AbstractDAO) aClass.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return (E) daomaps.get(aClass);
    }
}
