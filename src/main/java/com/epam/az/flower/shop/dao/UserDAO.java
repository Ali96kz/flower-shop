package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.dao.manager.CachedDAO;
import com.epam.az.flower.shop.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends CachedDAO<User> {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
    public static final Class<User> USER_CLASS = User.class;
    String FIND_BY_NAME_SQL = "Select User.id, User.password from User where User.nickName = ?";
    String FIND_BY_NAME_AND_PASSWORD_SQL = "Select User.id, User.password from User where " +
            "User.nickName = ? and User.password = ?";
    String USER_ID = "User.id";

    public int findByCredentials(String name, String password) throws DAOException {
        setGenericClass(USER_CLASS);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_BY_NAME_AND_PASSWORD_SQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            ResultSet resultSet = getSqlExecutor().executePreparedSqlQuery(preparedStatement);
            Integer id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt("User.id");
            }

            return id;
        } catch (SQLException e) {
            logger.error("can't create prepare statement", e);
            throw new DAOException("can't create prepare statement", e);
        }
    }

    public Integer findByCredentials(String name) throws DAOException {
        setGenericClass(USER_CLASS);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_BY_NAME_SQL);
            preparedStatement.setString(1, name);
            ResultSet resultSet = getSqlExecutor().executePreparedSqlQuery(preparedStatement);
            Integer id = 0;

            if (resultSet.next()) {
                id = resultSet.getInt(USER_ID);
            }

            return id;
        } catch (SQLException e) {
            logger.error("can't create prepared statement",e);
            throw new DAOException("can't create prepare statement", e);
        }
    }
}
