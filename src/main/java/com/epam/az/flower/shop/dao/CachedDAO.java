package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.entity.BaseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CachedDAO<E extends BaseEntity> extends AbstractDAO<E> {
    private Map<Integer, E> cache = new HashMap<>();
    private boolean insertNewEntity = false;
    private boolean getAll = false;

    @Override
    public E findById(int id) throws DAOException {
        if (cache.get(id) != null) {
            return cache.get(id);
        }
        E e = super.findById(id);
        cache.put(id, e);
        return e;
    }

    @Override
    public void update(E item) {
        super.update(item);
        cache.put(item.getId(), item);
    }

    @Override
    public int insert(E e) {
        cache.put(e.getId(), e);
        return super.insert(e);
    }

    @Override
    public List<E> getAll() {
        if (!getAll) {
            List<E> list = super.getAll();
            cache = new HashMap<>();
            for (E e : list) {
                cache.put(e.getId(), e);
            }

            List<E> items = new ArrayList<>(cache.values());
            getAll = true;
            return items;
        }
        return new ArrayList<>(cache.values());
    }

    @Override
    public void delete(E item) {
        cache.put(item.getId(), null);
        super.delete(item);
    }

    @Override
    public void delete(int id) {
        cache.put(id, null);
        super.delete(id);
    }

    public void deleteFromCache(int id){
        cache.put(id, null);
    }
}
