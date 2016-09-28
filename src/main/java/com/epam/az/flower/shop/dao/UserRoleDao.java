package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.dao.manager.CachedDAO;
import com.epam.az.flower.shop.entity.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleDao extends CachedDAO<UserRole> {
    public UserRole findUserRoleByName(String name) throws DAOException {
        String sql = "SELECT UserRole.id, UserRole.name FROM UserRole where name = '"+name+"'";
        ResultSet resultSet = null;
        try {
            resultSet = sqlExecutor.executeSqlQuery(sql, connection.createStatement());
        } catch (SQLException e) {
            throw new DAOException("can't crate statement", e);
        }
        try {
            UserRole userRole = new UserRole();

            if (resultSet.next()) {
                userRole.setId(resultSet.getInt("UserRole.id"));
                userRole.setName(resultSet.getString("UserRole.name"));
            }
            return userRole;
        } catch (SQLException e) {
            throw new DAOException("can't parse user role");
        }
    }
}
