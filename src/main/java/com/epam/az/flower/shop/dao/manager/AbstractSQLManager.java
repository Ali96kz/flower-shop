package com.epam.az.flower.shop.dao.manager;

import com.epam.az.flower.shop.entity.BaseEntity;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AbstractSQLManager {

    public static final String ENTITY_ID = "id";

    protected Date getTodayDay() {
        Calendar c = new GregorianCalendar();
        java.util.Date utilDate = c.getTime();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    protected String lowFirstLetter(String string) {
        char[] charArray = string.toCharArray();
        charArray[0] = Character.toLowerCase(charArray[0]);
        String result = new String(charArray);
        return result;
    }

    protected Integer getObjectId(Object object) {
        Integer result = null;
        try {
            Field id = BaseEntity.class.getDeclaredField(ENTITY_ID);
            id.setAccessible(true);
            result = (Integer) id.get(object);
        } catch (IllegalAccessException | NoSuchFieldException e1) {
            e1.printStackTrace();
        }
        return result;
    }

}
