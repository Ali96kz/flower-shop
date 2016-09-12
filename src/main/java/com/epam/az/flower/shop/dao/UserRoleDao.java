package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.entity.UserRole;

import java.sql.ResultSet;

public class UserRoleDao extends CachedDAO<UserRole> {
    public UserRole findUserRoleByName(String name) throws DAOException {
        String sql = "SELECT id, name FROM UserRole where name = '"+name+"'";
        ResultSet resultSet = executeSqlQuery(sql);
        UserRole userRole = parseResultSet(new UserRole(), resultSet);
        return userRole;
    }
}
