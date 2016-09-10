package com.epam.az.flower.shop.dao;

import java.util.List;

public interface DAO<E> {
    int insert(E e);

    E findById(int id) throws DAOException;

    void update(E item);

    List<E> getAll();
    void delete(int id);

    void delete(E item);

}
