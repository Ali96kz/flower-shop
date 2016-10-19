package com.epam.az.flowershop;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.dao.UserDAO;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.pool.ConnectionPool;

import java.sql.SQLException;

public abstract class AbstractTest {
    private UserDAO userDAO;
    private ConnectionPool connectionPool = new ConnectionPool();

    public User getUncacheUserById(int id) throws SQLException, DAOException {
        userDAO = new UserDAO();
        userDAO.setConnection(connectionPool.getConnection());
        User user = userDAO.findById(id);
        userDAO.getConnection().close();
        return user;
    }

}
