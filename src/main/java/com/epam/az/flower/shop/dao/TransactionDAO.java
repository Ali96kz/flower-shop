package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.dao.manager.CachedDAO;
import com.epam.az.flower.shop.entity.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO extends CachedDAO<Transaction> {
    public Transaction getTransactionByName(String name) throws DAOException {
        String sql = "SELECT Transaction.id, Transaction.name FROM Transaction where name = '" + name + "';";
        ResultSet resultSet;
        try {
            resultSet = sqlExecutor.executeSqlQuery(sql, connection.createStatement());
        } catch (SQLException e) {
            throw new DAOException("can't create statement", e);
        }

        try {
            Transaction transaction = new Transaction();
            if (resultSet.next()) {
                transaction.setId(resultSet.getInt("Transaction.id"));
                transaction.setName(resultSet.getString("Transaction.name"));
            }
            return transaction;
        } catch (SQLException e) {
            throw new DAOException("", e);
        }
    }
}
