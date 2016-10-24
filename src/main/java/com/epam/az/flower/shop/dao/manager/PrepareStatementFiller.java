package com.epam.az.flower.shop.dao.manager;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * fill prepared statement
 *
 * @param <E>
 */
public class PrepareStatementFiller<E extends BaseEntity> {
    private static final Logger logger = LoggerFactory.getLogger(CachedDAO.class);

    /**
     * fill preapare statement by object value
     *
     * @param preparedStatement
     * @param object
     * @throws DAOException
     */
    public void fillPrepareStatement(PreparedStatement preparedStatement, E object) throws DAOException {
        Field[] fields = object.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value = fields[i].get(object);
                if (value instanceof String) {
                    preparedStatement.setString(i + 1, (String) value);
                } else if (value instanceof Integer) {
                    preparedStatement.setInt(i + 1, (Integer) value);
                } else if (value instanceof Date) {
                    Date date = (Date) value;
                    preparedStatement.setDate(i + 1, date);
                } else if (value instanceof Boolean) {
                    preparedStatement.setBoolean(i + 1, (Boolean) value);
                } else if (value instanceof BaseEntity) {
                    int id = Util.getObjectId(value);
                    preparedStatement.setInt(i + 1, id);
                }
            }
        } catch (SQLException e) {
            logger.error("can't fill prepared statement", e);
            throw new DAOException("can't fill into prepared statement", e);
        } catch (IllegalAccessException e) {
            logger.error("try to get access to private field", e);
            throw new DAOException("try to get access to private object", e);
        }
    }

    /**
     * @param preparedStatement
     * @return fill prepared statement
     */
    public PreparedStatement fillDeleteStatement(PreparedStatement preparedStatement) throws DAOException {
        try {
            preparedStatement.setDate(1, Util.getTodayDay());
            return preparedStatement;
        } catch (SQLException e) {
            logger.error("can't fill delete statement", e);
            throw new DAOException("can't fill delete statement", e);
        }
    }
}
