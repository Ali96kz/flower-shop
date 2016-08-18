package com.epam.az.pool.DAO;

import com.epam.az.pool.entity.Flower;

import java.lang.reflect.Field;
import java.util.List;

public class FlowerDAO<T extends Flower> extends AbstractDAO<T> {

    public int insert(Flower flower) {
        StringBuilder sql = new StringBuilder("INSERT INTO " + flower.getClass().getSimpleName()
                + " (id, ");
        StringBuilder values = new StringBuilder("Values(" + flower.getId() + ", ");
        Field[] fields = flower.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                sql.append(field.getName() + ", ");
                if (field.get(flower) instanceof String) {
                    values.append("\'" + field.get(flower) + "\', ");
                } else if (field.getType().isPrimitive() || field.get(flower) instanceof Integer) {
                    values.append(field.get(flower) + ", ");

                }
            }
            deleteLastDot(sql);
            deleteLastDot(values);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return executeSql(sql + ")" + values + ");");
    }

    @Override
    public void update(T flower) {
        Field[] fields = flower.getClass().getDeclaredFields();
        StringBuilder sql = new StringBuilder("UPDATE "+flower.getClass().getSimpleName()+" SET ");

        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(flower) instanceof String) {
                    sql.append(field.getName() + "= '" + field.get(flower) + "', ");
                } else if (field.getType().isPrimitive() || field.get(flower) instanceof Integer) {
                    sql.append(field.getName() + " = " + field.get(flower)+", ");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        deleteLastDot(sql);
        sql.append(";");
        executeSql(sql.toString());
    }
}