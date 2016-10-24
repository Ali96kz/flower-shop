package com.epam.az.flower.shop.dao.manager;

import com.epam.az.flower.shop.dao.DAOException;
import com.epam.az.flower.shop.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CachedDAO<E extends BaseEntity> extends AbstractDAO<E> {
    private static final Logger logger = LoggerFactory.getLogger(CachedDAO.class);
    private Map<Integer, E> cache = new HashMap<>();
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
    public void update(E item) throws DAOException {
        try {
            super.update(item);
            cache.put(item.getId(), item);
        } catch (DAOException e) {
            logger.error("can't update project", e);
            throw new DAOException("can't update project", e);
        }
    }

    @Override
    public int insert(E item) throws DAOException {
        int id = 0;
        try {
            id = super.insert(item);
        } catch (DAOException e) {
            logger.error("can't insert object", e);
            throw new DAOException("can't insert", e);
        }
        cache.put(item.getId(), item);
        return id;
    }

    @Override
    public List<E> getAll() throws DAOException {
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
    public void delete(E item) throws DAOException {
        try {
            super.delete(item);
        } catch (DAOException e) {
            throw new DAOException("can't delete object", e);
        }
        cache.put(item.getId(), item);
    }

    @Override
    public void delete(int id) throws DAOException {
        try {
            super.delete(id);
        } catch (DAOException e) {
            throw new DAOException("can't delete user", e);
        }
        cache.remove(id);
    }

    public void deleteFromCache(int id) {
        cache.put(id, null);
    }
}
