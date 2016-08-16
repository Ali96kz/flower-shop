package com.epam.az.pool.DAO;

import com.epam.az.pool.pool.ConnectionPool;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public abstract class AbstractDAO<E> implements DAO<E> {
    private ConnectionPool connectionPool = new ConnectionPool();
    private Class MainClas;

    public void setMainClas(Class mainClas) {
        MainClas = mainClas;
    }

    public void insert(E e) {
        try {
            Field[] fields = e.getClass().getDeclaredFields();
            StringBuilder sql = new StringBuilder();
            StringBuilder values = new StringBuilder();
            sql.append("INSERT INTO " + e.getClass().getSimpleName() + "(");
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                sql.append(fields[i].getName() + ", ");
                Object value = fields[i].get(e);
                if (value instanceof String) {
                    values.append("\"" + value + "\", ");
                } else if (fields[i].getType().isPrimitive() || value instanceof  Integer) {
                    values.append(value + ", ");
                } else {
                    Integer id = getObjectId(fields[i], e);
                    values.append(id);
                }
            }
            deletLastDot(sql);
            deletLastDot(values);
            executeSql(sql + ")values(" + values + ");");
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    public void deletLastDot(StringBuilder stringBuilder) {
        for (int i = stringBuilder.length() - 1; i >= 0; i--) {
            if (stringBuilder.charAt(i) == ',') {
                stringBuilder.delete(i, stringBuilder.length());
                break;
            }
        }
    }

    private Integer getObjectId(Field field, E object) {
        Integer result = null;
        try {
            Field id = field.getClass().getDeclaredField("id");
            id.setAccessible(true);
            result = (Integer) id.get(object);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (NoSuchFieldException e1) {
            e1.printStackTrace();
        }
        return result;
    }

    public void update(E item) {
        StringBuilder sql = new StringBuilder("UPDATE " + item.getClass().getSimpleName() + " SET ");
        Field[] fields = item.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value = fields[i].get(item);
                if (value instanceof String) {
                    sql.append(fields[i].getName() + " = " + "\"" + value + "\"" + ", ");
                } else if (fields[i].getType().isPrimitive() || value instanceof Integer) {
                    sql.append(fields[i].getName() + " = " + value + ", ");
                } else {
                    sql.append(fields[i].getName() + " = " + value + ", ");
                    Integer id = getObjectId(fields[i], item);
                    sql.append(id);
                }
            }
            deletLastDot(sql);
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

    @Override
    public E findById(int id) {
        E result = null;
        try {
            result = (E) MainClas.newInstance();
            String selectSQL = createSelectSQL(MainClas);
            ResultSet resultSet = executeSqlQuery(selectSQL + "where id = " + id);
            if (resultSet.next())
                parseResultSet(result, resultSet);
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<E> getAll() {
        List<E> resultList = new ArrayList<>();
        String selectSQL = createSelectSQL(MainClas);
        try {
            ResultSet resultSet = executeSqlQuery(selectSQL + ";");
            while (resultSet.next()) {
                E e = parseResultSet((E) MainClas.newInstance(), resultSet);
                resultList.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return resultList;
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
        } catch (SQLException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        return e;
    }
}
