package com.epam.az.pool.DAO;

import java.util.List;

public interface DAO<E> {
    public void insert(E e);
    public E findById(int id);
    public void update(E item);
    public List<E> getAll();
    public void delete(E item);

}
