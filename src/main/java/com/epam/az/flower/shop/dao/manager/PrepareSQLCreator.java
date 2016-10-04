package com.epam.az.flower.shop.dao.manager;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.entity.BaseEntity;
import java.lang.reflect.Field;
import java.sql.Date;

public class PrepareSQLCreator<E extends BaseEntity> extends AbstractSQLManager{

    public void createInsertSQL(StringBuilder sql, StringBuilder values, E object) throws DAOException {
        sql.append("INSERT INTO " + object.getClass().getSimpleName() + "(");

        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Class fieldSuperclass = fields[i].getType().getSuperclass();
            String name = fields[i].getName();

            if (fieldSuperclass == BaseEntity.class) {
                sql.append(lowFirstLetter(name) + "Id, ");
            } else {
                sql.append(name + ", ");
            }
            values.append("?, ");
        }

        deleteLastDot(sql);
        deleteLastDot(values);
    }

    public void deleteLastDot(StringBuilder stringBuilder) {
        for (int i = stringBuilder.length() - 1; i >= 0; i--) {
            if (stringBuilder.charAt(i) == ',') {
                stringBuilder.delete(i, stringBuilder.length());
                break;
            }
        }
    }

    public String createSqlForFindById(Class genericClass, int id){
       String sql = "SELECT " + createSQL(genericClass) + " where " + genericClass.getSimpleName() +
                ".id = " + id +";";
        return sql;
    }

    public String createSQL(Class clazz) {
        StringBuilder sql = new StringBuilder();

        Field[] fields = clazz.getDeclaredFields();
        sql.append(clazz.getSimpleName() + ".id, ");
        sql.append(clazz.getSimpleName() + ".deleteDay, ");

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType().getSuperclass() == BaseEntity.class) {
                sql.append(lowFirstLetter(fields[i].getType().getSimpleName()) + "Id, ");
            } else {
                sql.append(clazz.getSimpleName() + "." + fields[i].getName() + ", ");
            }
        }

        deleteLastDot(sql);
        return sql.toString() + " FROM " + clazz.getSimpleName();
    }

    public String createSQLForUpdate(E item) throws DAOException {
        try {
            StringBuilder sql = new StringBuilder("UPDATE " + item.getClass().getSimpleName() + " SET ");
            Field[] fields = item.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value = fields[i].get(item);
                if (value instanceof String) {
                    sql.append(fields[i].getName() + " =  ?, ");
                } else if (fields[i].getType().isPrimitive() || value instanceof Integer) {
                    sql.append(fields[i].getName() + " = ?, ");
                } else if (value instanceof Date) {
                    sql.append(fields[i].getName() + " = ?, ");
                } else {
                    sql.append(fields[i].getName() + "Id = ?,");
                }
            }
            deleteLastDot(sql);
            sql.append(" where id = " + getObjectId(item) + ";");

            return sql.toString();
        } catch (IllegalAccessException e) {
            throw new DAOException("try to get value of private field", e);
        }
    }

    public String createSQLForDelete(E item){
        try {
            Field field = item.getClass().getDeclaredField("id");
            field.setAccessible(true);
            String sql = ("UPDATE " + item.getClass().getSimpleName() + " set deleteDAY = ? where id = " + field.get(item) + ";");
            return sql;
        } catch (NoSuchFieldException e) {
            throw new UnsupportedOperationException();
        } catch (IllegalAccessException e) {
            throw new UnsupportedOperationException();
        }
    }

    public String createSQLForDelete(int id, Class genericClass){
        String sql = ("UPDATE " + genericClass.getSimpleName() + " set deleteDAY = ? where id = " + id + ";");
        return sql;
    }
    public String createSqlForGetAll(Class genericClass){
        return  "SELECT "+ createSQL(genericClass)+";";
    }
}
