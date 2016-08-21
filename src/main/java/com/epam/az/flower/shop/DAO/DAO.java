package com.epam.az.flower.shop.DAO;

import java.util.List;

public interface DAO<E> {
    int insert(E e);

    E findById(int id);

    void update(E item);

    List<E> getAll();

    void delete(E item);

}
