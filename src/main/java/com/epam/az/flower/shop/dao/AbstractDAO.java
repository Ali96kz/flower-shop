package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.entity.BaseEntity;
import com.epam.az.flower.shop.pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public abstract class AbstractDAO<E extends BaseEntity> implements DAO<E> {
    protected ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Class genericClass;
    private Logger logger = LoggerFactory.getLogger(AbstractDAO.class);


    @Override
    public E findById(int id) {
        E result = null;

        try {
            result = getGenericClass().newInstance();
            String selectSQL = createSQL(getGenericClass());
            ResultSet resultSet = executeSqlQuery("SELECT " + selectSQL + " where " + getGenericClass().getSimpleName()
                    + ".id = " + id + ";");
            if (resultSet.next())
                result = parseResultSet(result, resultSet);
            return result;
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            logger.trace("Find by id", e);
        }
        return result;
    }

    @Override
    public void delete(E item) {
        try {
            Field field = item.getClass().getDeclaredField("id");
            field.setAccessible(true);

            Calendar c = new GregorianCalendar();
            java.util.Date utilDate = c.getTime();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            String sql = ("UPDATE " + item.getClass().getSimpleName() + " set deleteDAY = '" + sqlDate + "' where id = " + field.get(item) + ";");
            executeSql(sql);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

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
                    sql.append(fields[i].getName() + " = " + "\'" + value + "\'" + ", ");
                } else if (fields[i].getType().isPrimitive() || value instanceof Integer) {
                    sql.append(fields[i].getName() + " = " + value + ", ");
                } else if (value instanceof Date) {
                    Date date = (Date) value;
                    sql.append(fields[i].getName() + " = \'" + date + "\', ");
                } else {
                    sql.append(fields[i].getName() + "Id = " + getObjectId(value) + ", ");
                }
            }
            deleteLastDot(sql);
            sql.append(" where id = " + getObjectId(item) + ";");
            executeSql(sql.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<E> getAll() {
        List<E> resultList = new ArrayList<>();

        String selectSQL = createSQL(getGenericClass());
        try {
            ResultSet resultSet = executeSqlQuery("SELECT " + selectSQL + ";");
            while (resultSet.next()) {
                E e = parseResultSet(getGenericClass().newInstance(), resultSet);
                resultList.add(e);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return resultList;
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
                String name = fields[i].getName();
                if (value instanceof String) {
                    values.append("\'" + value + "\', ");
                } else if (fields[i].getType().isPrimitive() || value instanceof Integer) {
                    values.append(value + ", ");
                } else if (value instanceof Date) {
                    Date date = (Date) value;
                    values.append("\'" + date.toString() + "\', ");
                } else if (value instanceof BaseEntity) {
                    int id = getObjectId(value);
                    name += "Id";
                    values.append(id + ", ");
                }
                sql.append(name + ", ");

            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
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

    private Integer getObjectId(Object object) {
        Integer result = null;
        try {
            Field id = BaseEntity.class.getDeclaredField("id");
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
        try {
            connection = connectionPool.getConnection();

            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute(sql, Statement.RETURN_GENERATED_KEYS);

            connection.commit();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.trace("SQL " + sql, e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return result;
    }


    protected String createSQL(Class clazz) {
        StringBuilder sql = new StringBuilder();
        StringBuilder join = new StringBuilder();
        fillSql(sql, join, clazz);
        deleteLastDot(sql);
        deleteLastDot(join);
        return sql.toString() + " FROM " + getGenericClass().getSimpleName() + " " + join.toString() + "";
    }

    protected String createJoin(Class clazz, StringBuilder join) {
        StringBuilder sql = new StringBuilder();
        fillSql(sql, join, clazz);
        deleteLastDot(sql);
        return sql.toString();
    }

    protected void fillSql(StringBuilder sql, StringBuilder join, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        sql.append(clazz.getSimpleName() + ".id, ");
        sql.append(clazz.getSimpleName() + ".deleteDay, ");
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType().getSuperclass() == BaseEntity.class) {
                join.append(" INNER JOIN " + fields[i].getType().getSimpleName() + " on " +
                        fields[i].getType().getSimpleName() + ".Id = " + clazz.getSimpleName() + "." + lowFirstLetter(fields[i].getType().getSimpleName()) + "Id");
                sql.append(createJoin(fields[i].getType(), join) + ", ");
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
            System.out.println(sql);
            connection = connectionPool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql.toString());
        } catch (SQLException e) {
            logger.trace("SQL " + sql, e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return resultSet;
    }

    public E parseResultSet(E e, ResultSet resultSet) {
        try {
            Field[] fields = e.getClass().getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value = getValue(fields[i], resultSet);
                fields[i].set(e, value);
            }

            e.setId(resultSet.getInt(e.getClass().getSimpleName() + ".id"));
            e.setDeleteDay(resultSet.getDate(e.getClass().getSimpleName() + ".deleteDay"));
            return e;
        } catch (SQLException | IllegalAccessException | InstantiationException e1) {
            e1.printStackTrace();
        }
        return e;
    }

    public Object getValue(Field field, ResultSet resultSet) throws SQLException, IllegalAccessException, InstantiationException {
        Class fieldType = field.getType();
        if (fieldType == Integer.class || fieldType == int.class) {
            int value = resultSet.getInt(field.getName());
            return value;
        } else if (fieldType == String.class) {
            String value = resultSet.getString(field.getName());
            return value;
        } else if (fieldType == boolean.class) {
            boolean value = resultSet.getBoolean(field.getName());
            return value;
        } else if (fieldType == Date.class) {
            Date value = resultSet.getDate(field.getName());
            return value;
        } else {
            E value = (E) fieldType.newInstance();
            parseResultSet(value, resultSet);
            return value;
        }
    }

    public String lowFirstLetter(String string) {
        char[] charArray = string.toCharArray();
        charArray[0] = Character.toLowerCase(charArray[0]);
        String result = new String(charArray);
        return result;
    }
}
