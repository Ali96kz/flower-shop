package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.dao.manager.CachedDAO;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.User;
import com.epam.az.flower.shop.entity.UserOrder;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserOrderDAO extends CachedDAO<UserOrder> {
    public List<UserOrder> executeChooseOrder(int id, Date orderDate) throws DAOException {
        try {
            String sqlCall = "{call chooseOrder(?, ?)}";
            CallableStatement cstmt = getConnection().prepareCall(sqlCall);
            cstmt.setInt("userID", id);
            cstmt.setDate("orderDay", orderDate);
            ResultSet resultSet =  cstmt.executeQuery();
            List<UserOrder> orders = new ArrayList<>();
            while (resultSet.next()) {
                UserOrder order = new UserOrder();
                Product product = new Product();
                User user = new User();
                product.setId(resultSet.getInt("productId"));
                user.setId(resultSet.getInt("userId"));

                order.setOrderDate(resultSet.getDate("orderDate"));
                order.setProduct(product);
                order.setUser(user);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DAOException("", e);
        }
    }
}
