package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.util.Hasher;
import com.epam.az.flower.shop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends CachedDAO<User> {

    public Integer findByCredentials(String nickName, String password) throws DAOException {
        setGenericClass(User.class);
        Hasher hasher = new Hasher();
        password = hasher.hash(password);
        String sql = "Select "+ createSQL(User.class) + " WHERE User.nickName = " +"'" +nickName +"'";
        ResultSet resultSet = executeSqlQuery(sql);
        Integer userId = null;
        try {
            if(resultSet.next()){
                String dbPassword =  resultSet.getString("password");
                userId = resultSet.getInt("id");
                if (dbPassword.equals(password)) {
                    return userId;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }
}
