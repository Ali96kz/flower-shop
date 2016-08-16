package com.epam.az.pool.DAO;

import com.epam.az.pool.pool.ConnectionPool;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO<E> implements DAO<E> {
    ConnectionPool connectionPool = new ConnectionPool();

    public void insert(E e) {
        try {
            Field[] fields = e.getClass().getDeclaredFields();
            StringBuilder sql = new StringBuilder();
            StringBuilder values = new StringBuilder();
            sql.append("INSERT INTO " + e.getClass().getSimpleName() + "(");

            for (int i = 0; i < fields.length; i++) {
                sql.append(fields[i].getName());
                fields[i].setAccessible(true);
                Object object = fields[i].get(e);
                if (i == fields.length - 1) {
                    if (object instanceof String) {
                        values.append("\"" + object + "\"");
                    } else
                        values.append(object);
                } else {
                    if (object instanceof String) {
                        values.append("\"" + object + "\",");
                    } else
                        values.append(object + ",");
                }
            }
            sql.append(")VALUES(" + values + ");");
            Statement statement = connectionPool.getConn().createStatement();
            statement.execute(sql.toString());
        } catch (SQLException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
    }

    public E findById(int id) {
        throw new UnsupportedOperationException();
    }

    public void update(E item) {
        StringBuilder sql = new StringBuilder("UPDATE " + item.getClass().getSimpleName() + " SET ");
        Field[] fields = item.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value = fields[i].get(item);
                if (i == fields.length - 1) {
                    if (value instanceof String) {
                        sql.append(fields[i].getName() + " = " + "\"" + value + "\"" );
                    } else
                        sql.append(fields[i].getName() + " = " + value );
                } else {
                    if (value instanceof String) {
                        sql.append(fields[i].getName() + " = " + "\"" + value + "\"" + ", ");
                    } else
                        sql.append(fields[i].getName() + " = " + value + ", ");
                }
            }
            sql.append(" where id = "+ fields[0].get(item)+";");
            Statement statement = connectionPool.getConn().createStatement();
            statement.execute(sql.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<E> getAll() {
        throw new UnsupportedOperationException();
    }

    public void delete(E item) {

    }
}
