package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.dao.manager.CachedDAO;
import com.epam.az.flower.shop.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleDao extends CachedDAO<UserRole> {

    private static final String SQL_SELECT = "SELECT UserRole.id, UserRole.name FROM UserRole where name = '";
    private static final String USER_ROLE_ID = "UserRole.id";
    private static final String USER_ROLE_NAME = "UserRole.name";
    private Logger logger = LoggerFactory.getLogger(UserRoleDao.class);

    public UserRole findUserRoleByName(String name) throws DAOException {
        String sql = SQL_SELECT + name + "'";
        try {
            ResultSet resultSet = getSqlExecutor().executeSqlQuery(sql, getConnection().createStatement());
            UserRole userRole = new UserRole();

            if (resultSet.next()) {
                userRole.setId(resultSet.getInt(USER_ROLE_ID));
                userRole.setName(resultSet.getString(USER_ROLE_NAME));
            }
            return userRole;
        } catch (SQLException e) {
            logger.error("can't parse user role", e);
            throw new DAOException("can't parse user role", e);
        }
    }
}
