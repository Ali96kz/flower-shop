package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.entity.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO extends CachedDAO<Transaction> {
    public Transaction getTransactionByName(String name) throws DAOException {
        String sql = "SELECT Transaction.id, Transaction.name, Transaction.deleteDay FROM Transaction WHERE name = '" + name+"';";
        ResultSet resultSet = executeSqlQuery(sql);
        try {
            if (resultSet.next()) {
                Transaction transaction = parseResultSet(new Transaction(), resultSet);
                return transaction;
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException("", e);
        }
    }
}
