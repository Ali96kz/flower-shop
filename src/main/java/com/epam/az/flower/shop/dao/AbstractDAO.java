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
    private Connection connection;
    private Class genericClass;
    private Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

    @Override
    public void delete(int id) throws DAOException {
        String sql = ("UPDATE " + genericClass.getSimpleName() + " set deleteDAY = '" + getTodayDay() + "' where id = " + id + ";");
        executeSql(sql);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public E findById(int id) throws DAOException {
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
        } catch (DAOException e) {
            throw new DAOException("problem with parsing", e);
        }
        return result;
    }

    @Override
    public void delete(E item) throws DAOException {
        try {
            Field field = item.getClass().getDeclaredField("id");
            field.setAccessible(true);

            String sql = ("UPDATE " + item.getClass().getSimpleName() + " set deleteDAY = '" + getTodayDay() + "' where id = " + field.get(item) + ";");
            executeSql(sql);
        } catch (NoSuchFieldException e) {
            throw new DAOException("can't find field with that name", e);
        } catch (IllegalAccessException e) {
            throw new DAOException("try to get value of private field", e);
        }

    }

    @Override
    public int insert(E e) throws DAOException {
        StringBuilder sql = new StringBuilder();
        StringBuilder values = new StringBuilder();
        sql.append("INSERT INTO " + getGenericClass().getSimpleName() + "(");
        fillSqlAndValue(sql, values, e);
        return executeSql(sql + ")values(" + values + ");");
    }

    @Override
    public void update(E item) throws DAOException {
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
            throw new DAOException("try to get value of private field", e);
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
        } catch (DAOException e) {
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

    public void setGenericClass(Class genericClass) {
        this.genericClass = genericClass;
    }

    public void fillSqlAndValue(StringBuilder sql, StringBuilder values, E e) {
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


    protected int executeSql(String sql) throws DAOException {
        logger.info("dao sql exe sql  = {}", sql);
        int result = 0;
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("can't execute sql", e);
        }
        return result;
    }


    protected String createSQL(Class clazz) {
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

    public ResultSet executeSqlQuery(String sql) throws DAOException {
        logger.info("execute sql query {}", sql);
        Statement statement;
        ResultSet resultSet ;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql.toString());
        } catch (SQLException e1) {
            throw new DAOException("can't execute sql", e1);
        }
        return resultSet;

    }

    public E parseResultSet(E object, ResultSet resultSet) throws DAOException {
        try {
            Field[] fields = object.getClass().getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value = getValue(fields[i], resultSet);
                fields[i].set(object, value);
            }

            object.setId(resultSet.getInt(object.getClass().getSimpleName() + ".id"));
            object.setDeleteDay(resultSet.getDate(object.getClass().getSimpleName() + ".deleteDay"));
            return object;
        } catch (SQLException | IllegalAccessException e1) {
            throw new DAOException("parse resulSet error", e1);
        }
    }

    public Object getValue(Field field, ResultSet resultSet) throws DAOException {
        try {


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
                Field idField = BaseEntity.class.getDeclaredField("id");
                idField.setAccessible(true);
                E value = (E) fieldType.newInstance();
                idField.set(value, resultSet.getInt(lowFirstLetter(fieldType.getSimpleName()) + "Id"));
                return value;
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            throw new DAOException("can't get value ", e);
        }
    }

    public String lowFirstLetter(String string) {
        char[] charArray = string.toCharArray();
        charArray[0] = Character.toLowerCase(charArray[0]);
        String result = new String(charArray);
        return result;
    }

    public java.sql.Date getTodayDay() {
        Calendar c = new GregorianCalendar();
        java.util.Date utilDate = c.getTime();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }
}
