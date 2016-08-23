package com.epam.az.flower.shop.DAO;

import com.epam.az.flower.shop.entity.BaseEntity;
import com.epam.az.flower.shop.pool.ConnectionPool;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public abstract class AbstractDAO<E extends BaseEntity> implements DAO<E> {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Class genericClass;

    @Override
    public E findById(int id) {
        E result = null;
        try {
            result = getGenericClass().newInstance();
            String selectSQL = createSelectSQL(getGenericClass());
            ResultSet resultSet = executeSqlQuery(selectSQL + " where id = " + id + ";");
            if (resultSet.next())
                parseResultSet(result, resultSet);
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insert(E e) {
        StringBuilder sql = new StringBuilder();
        StringBuilder values = new StringBuilder();
        sql.append("INSERT INTO " + getGenericClass().getSimpleName() + "(");
        fillSqlAndVAlue(sql, values, e);

        return executeSql(sql + ")values(" + values + ");");

    }

    @Override
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
            deleteLastDot(sql);
            sql.append(" where id = " + fields[0].get(item) + ";");
            executeSql(sql.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<E> getAll() {
        List<E> resultList = new ArrayList<>();
        String selectSQL = createSelectSQL(getGenericClass());
        try {
            ResultSet resultSet = executeSqlQuery(selectSQL + ";");
            while (resultSet.next()) {
                E e = parseResultSet(getGenericClass().newInstance(), resultSet);
                resultList.add(e);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public void setGenericClass(Class genericClass) {
        this.genericClass = genericClass;
    }

    public Class<E> getGenericClass() {
        if (genericClass == null) {
            Type mySuperClass = getClass().getGenericSuperclass();
            Type tType = ((ParameterizedType) mySuperClass).getActualTypeArguments()[0];
            String className = tType.toString().split(" ")[1];
            try {
                genericClass = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return genericClass;
    }

    public void fillSqlAndVAlue(StringBuilder sql, StringBuilder values, E e) {
        Field[] fields = getGenericClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value = fields[i].get(e);
                if (fields[i].getName().equalsIgnoreCase("id")) {
                    if (value != null) {
                        sql.append("id, ");
                        values.append(value + " , ");
                    }
                } else {
                    sql.append(fields[i].getName() + ", ");
                    if (value instanceof String ) {
                        values.append("\'" + value + "\', ");
                    } else if (fields[i].getType().isPrimitive() || value instanceof Integer) {
                        values.append(value + ", ");
                    }else if(value instanceof  Date){
                        Date date = (Date)value;
                        values.append("\'" + date.toString() + "\', ");
                    }
                }
            }
            deleteLastDot(sql);
            deleteLastDot(values);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }


    }

    public void deleteLastDot(StringBuilder stringBuilder) {
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
        } catch (IllegalAccessException | NoSuchFieldException e1) {
            e1.printStackTrace();
        }
        return result;
    }

    protected int executeSql(String sql) {
        Connection connection = null;
        int result = 0;
        System.out.println(sql);
        try {
            connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return result;
    }

    protected String createSelectSQL(Class clazz) {
        StringBuilder sql = new StringBuilder();
        StringBuilder join = new StringBuilder();

        createSql(sql, join, clazz);

        deleteLastDot(sql);
        return sql.toString() + " FROM " + getGenericClass().getSimpleName() + " " + join.toString() + "";
    }


    protected String createSelectSQL(Class clazz, StringBuilder join) {
        StringBuilder sql = new StringBuilder();
        createSql(sql, join, clazz);
        deleteLastDot(sql);
        return sql.toString();
    }

    protected void createSql(StringBuilder sql, StringBuilder join, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType().getSuperclass() == BaseEntity.class) {
                join.append(" INNER JOIN " + fields[i].getType().getSimpleName() + " on " +
                        fields[i].getType().getSimpleName() + ".Id = " + clazz.getSimpleName() + "." + lowFirstLetter(fields[i].getType().getSimpleName()) + "Id");
                sql.append(createSelectSQL(fields[i].getType(), join) + ", ");
            } else {
                sql.append(clazz.getSimpleName() + "." + fields[i].getName() + ", ");
            }
        }
    }

    public ResultSet executeSqlQuery(String sql) {
        Statement statement;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection("root", "root");
            statement = connection.createStatement();
            System.out.println(sql.toString());
            resultSet = statement.executeQuery(sql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
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

    public E parseResultSet(E e, ResultSet resultSet) {
        try {
            Field[] fields = e.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                if (fields[i].getType() == Integer.class) {
                    int value = resultSet.getInt(fields[i].getName());
                    fields[i].set(e, value);
//                    TODO
                } else if (fields[i].getType() == String.class || fields[i].getType().isPrimitive()) {
                    String value = resultSet.getString(fields[i].getName());
                    fields[i].set(e, value);
                } else {
                    Class clazz = fields[i].getType();
                    E value = (E) clazz.newInstance();
                    parseResultSet(value, resultSet);
                    fields[i].set(e, value);
                }
            }
        } catch (SQLException | IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        }

        return e;
    }

    public String lowFirstLetter(String string) {
        char[] charArray = string.toCharArray();
        charArray[0] = Character.toLowerCase(charArray[0]);
        String result = new String(charArray);
        return result;
    }
}
