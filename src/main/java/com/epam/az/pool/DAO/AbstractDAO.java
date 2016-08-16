package com.epam.az.pool.DAO;

import com.epam.az.pool.pool.ConnectionPool;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public abstract class AbstractDAO<E> implements DAO<E> {
    private ConnectionPool connectionPool = new ConnectionPool();
    private Field[] fields;

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
            executeSql(sql.toString());
        } catch (IllegalAccessException e1) {
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
                        sql.append(fields[i].getName() + " = " + "\"" + value + "\"");
                    } else
                        sql.append(fields[i].getName() + " = " + value);
                } else {
                    if (value instanceof String) {
                        sql.append(fields[i].getName() + " = " + "\"" + value + "\"" + ", ");
                    } else
                        sql.append(fields[i].getName() + " = " + value + ", ");
                }
            }
            sql.append(" where id = " + fields[0].get(item) + ";");
            executeSql(sql.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void executeSql(String sql) {
        try {
            Statement statement = connectionPool.getConn().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String createSelectSQL(Class aclass) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        Field[] fields = aclass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields.length - 1 == i) {
                sql.append(fields[i].getName());
            } else
                sql.append(fields[i].getName() + ", ");
        }
        sql.append(" From Origin");
        return sql.toString();
    }

    public ResultSet executeSqlQuery(String sql) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connectionPool.getConn().createStatement();
            resultSet = statement.executeQuery(sql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public List<E> getAll() {
        throw new UnsupportedOperationException();
    }

    public void delete(E item) {
        try {
            Field field = item.getClass().getDeclaredField("id");
            field.setAccessible(true);

            Calendar c = new GregorianCalendar();
            java.util.Date utilDate = c.getTime();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            String sql = ("UPDATE " + item.getClass().getSimpleName() + " set deleteDAY = '" + sqlDate + "' where id = " + field.get(item) + ";");
            System.out.println(sql);
            executeSql(sql);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public E parseResultSet(E e, ResultSet resultSet) {
        try {
            Field[] fields = e.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                if (fields[i].getType() == Integer.class) {
                    int value = resultSet.getInt(fields[i].getName());
                    fields[i].set(e, value);

                } else {
                    String value = resultSet.getString(fields[i].getName());
                    fields[i].set(e, value);
                    System.out.println(value);
                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
        return e;
    }
}
