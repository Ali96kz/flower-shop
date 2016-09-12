package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.entity.Transaction;

import java.sql.ResultSet;

public class TransactionDAO extends CachedDAO<Transaction> {
    public Transaction getTransactionByName(String name) throws DAOException {
        String sql = "SELECT id, name FROM Transaction WHERE name = '" + name+"';";
        ResultSet resultSet = executeSqlQuery(sql);
        Transaction transaction = parseResultSet(new Transaction(), resultSet);
        return transaction;
    }
}
