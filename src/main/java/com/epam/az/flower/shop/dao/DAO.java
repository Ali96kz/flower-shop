package com.epam.az.flower.shop.dao;

import java.util.List;

public interface DAO<E> {
    int insert(E e) throws DAOException;

    E findById(int id) throws DAOException;

    void update(E item) throws DAOException;

    List<E> getAll() throws DAOException;
    void delete(int id) throws DAOException;

    void delete(E item) throws DAOException;

}
