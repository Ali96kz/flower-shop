package com.epam.az.flower.shop.dao.manager;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.entity.BaseEntity;

import java.lang.reflect.Field;
import java.sql.Date;

/**
 * SQLCreator create select sql, For insert, update or delete  create sql for prepared statement
 *  For example: insert into Origin(province, country)values(?, ?)
 * @param <E>
 */
public class SQLCreator<E extends BaseEntity> {

    public static final String SQL_INSERT_INTO = "INSERT INTO ";
    public static final String VALUES = ")values(";
    public static final String SQL_SELECT = "SELECT ";
    public static final String SQL_WHERE = " where ";
    public static final String SQL_UPDATE = "UPDATE ";
    public static final String PREPARED_STATEMENT_PLACEHOLDER = " =  ?, ";
    public static final String SET_DELETE_DAY_WHERE_ID = " set deleteDAY = ? where id = ";
    public static final String SQL_WHERE_ID = " where id = ";
    public static final String DECLARED_FIELD_ID = "id";
    public static final String SQL_FROM = " FROM ";
    public static final String PREAPARED_STATEMENT_SET = " SET ";

    /**
     * create insert sql for prepared statement.
     *
     * @param  entityClass class of entity which you want insert into data
     * @return prepare sql
     * @throws DAOException
     */
    public String createPrepareInsertSQL(Class entityClass) throws DAOException {
        StringBuilder sql = new StringBuilder();
        StringBuilder values = new StringBuilder();

        sql.append(SQL_INSERT_INTO + entityClass.getSimpleName() + "(");

        Field[] fields = entityClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Class fieldSuperclass = fields[i].getType().getSuperclass();
            String name = fields[i].getName();

            if (fieldSuperclass == BaseEntity.class) {
                sql.append(Util.lowFirstLetter(name) + "Id, ");
            } else {
                sql.append(name + ", ");
            }
            values.append("?, ");
        }

        deleteLastDot(sql);
        deleteLastDot(values);
        return sql + VALUES + values + ");";
    }

    private void deleteLastDot(StringBuilder stringBuilder) {
        for (int i = stringBuilder.length() - 1; i >= 0; i--) {
            if (stringBuilder.charAt(i) == ',') {
                stringBuilder.delete(i, stringBuilder.length());
                break;
            }
        }
    }

    /**
     * create sql for find by id.
     *
     * @param entityClass
     * @param id
     * @return sql string
     */
    public String createStatementSqlForFindById(Class entityClass, int id) {
        String sql = SQL_SELECT + createSQL(entityClass) + SQL_WHERE + entityClass.getSimpleName() +
                ".id = " + id + ";";
        return sql;
    }

    protected String createSQL(Class clazz) {
        StringBuilder sql = new StringBuilder();

        Field[] fields = clazz.getDeclaredFields();
        sql.append(clazz.getSimpleName() + ".id, ");
        sql.append(clazz.getSimpleName() + ".deleteDay, ");

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType().getSuperclass() == BaseEntity.class) {
                sql.append(Util.lowFirstLetter(fields[i].getType().getSimpleName()) + "Id, ");
            } else {
                sql.append(clazz.getSimpleName() + "." + fields[i].getName() + ", ");
            }
        }

        deleteLastDot(sql);
        return sql.toString() + SQL_FROM + clazz.getSimpleName();
    }

    /**
     * create update sql for prepared statement
     *
     * @param item
     * @return
     * @throws DAOException
     */
    public String createSQLForUpdate(E item) throws DAOException {
        try {
            StringBuilder sql = new StringBuilder(SQL_UPDATE + item.getClass().getSimpleName() + PREAPARED_STATEMENT_SET);
            Field[] fields = item.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value = fields[i].get(item);
                if (value instanceof String) {
                    sql.append(fields[i].getName() + PREPARED_STATEMENT_PLACEHOLDER);
                } else if (fields[i].getType().isPrimitive() || value instanceof Integer) {
                    sql.append(fields[i].getName() + PREPARED_STATEMENT_PLACEHOLDER);
                } else if (value instanceof Date) {
                    sql.append(fields[i].getName() + PREPARED_STATEMENT_PLACEHOLDER);
                } else {
                    sql.append(fields[i].getName() + "Id" + PREPARED_STATEMENT_PLACEHOLDER);
                }
            }
            deleteLastDot(sql);
            sql.append(SQL_WHERE_ID + Util.getObjectId(item) + ";");

            return sql.toString();
        } catch (IllegalAccessException e) {
            throw new DAOException("try to get value of private field", e);
        }
    }

    /**
     *  Don't create delete sql. just mark row as deleted
     * @param item
     * @return sql
     */
    public String createSQLForDelete(E item) {
        try {
            Field field = item.getClass().getDeclaredField(DECLARED_FIELD_ID);
            field.setAccessible(true);
            String sql = createSQLForDelete(item.getId(), item.getClass());
            return sql;
        } catch (NoSuchFieldException e) {
            throw new UnsupportedOperationException();
        }
    }

    public String createSQLForDelete(int id, Class genericClass) {
        String sql = (SQL_UPDATE + genericClass.getSimpleName() + SET_DELETE_DAY_WHERE_ID + id + ";");
        return sql;
    }

    /**
     *
     * @param entityClass
     * @return sql for get all row from data
     */
    public String createSqlForGetAll(Class entityClass) {
        return SQL_SELECT + createSQL(entityClass) + ";";
    }
}
