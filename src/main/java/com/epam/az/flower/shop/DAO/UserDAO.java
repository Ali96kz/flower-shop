package com.epam.az.flower.shop.DAO;

import com.epam.az.flower.shop.util.Hasher;
import com.epam.az.flower.shop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO<User> {
    public Integer findByCredentials(String nickName, String password) {
        Hasher hasher = new Hasher();
        password = hasher.hash(password);
        String sql = "Select "+ createJoin(User.class) + " WHERE User.nickName = " +"'" +nickName +"'";
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
