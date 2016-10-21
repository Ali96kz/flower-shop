package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.dao.manager.CachedDAO;
import com.epam.az.flower.shop.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends CachedDAO<User> {
    public static final String FIND_BY_NAME_SQL = "Select User.id, User.password from User where User.nickName = ?";
    public static final String FIND_BY_NAME_AND_PASSWORD_SQL = "Select User.id, User.password from User where " +
            "User.nickName = ? and User.password = ?";

    public int findByCredentials(String name, String password) throws DAOException {
        setGenericClass(User.class);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_AND_PASSWORD_SQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            ResultSet resultSet = sqlExecutor.executePreaparedSqlQuery(preparedStatement);
            Integer id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt("User.id");
            }

            return id;
        } catch (SQLException e) {
            throw new DAOException("can't create prepare statement", e);
        }
    }

    public Integer findByCredentials(String name) throws DAOException {
        setGenericClass(User.class);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_SQL);
            preparedStatement.setString(1, name);
            ResultSet resultSet = sqlExecutor.executePreaparedSqlQuery(preparedStatement);
            Integer id = 0;

            if (resultSet.next()) {
                id = resultSet.getInt("User.id");
            }

            return id;
        } catch (SQLException e) {
            throw new DAOException("can't create prepare statement", e);
        }
    }
}
