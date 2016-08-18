package com.epam.az.pool.service;

import com.epam.az.pool.DAO.AbstractDAO;
import com.epam.az.pool.DAO.FlowerDAO;
import com.epam.az.pool.DAO.ProductDAO;
import com.epam.az.pool.DAO.UnityDAO;
import com.epam.az.pool.entity.BaseEntity;
import com.epam.az.pool.entity.Product;
import com.epam.az.pool.entity.SyntheticFlower;

import java.util.List;

public class ProductService<T extends Product> {
    AbstractDAO <Product> abstractDAO = new ProductDAO();
    AbstractDAO <Product> flowerDao =new FlowerDAO();
    public void insert(Product product) {

        int id = abstractDAO.insert(product);
        product.setId(id);
        flowerDao.insert(product);
    }

    public void update(Product product) {
        abstractDAO.update(product);
        flowerDao.update(product);
    }

    public T findById(int id) {

        Product product = abstractDAO.findById(id);
        UnityDAO unityDAO = new UnityDAO(product.getClass());
        T result = (T) unityDAO.findById(id);
        result.setName(product.getName());
        return result;
    }

    public List<T> getAll() {

        List<T> products = (List<T>) abstractDAO.getAll();
        List<T> result = (List<T>) flowerDao.getAll();
        int i = 0;

        for (T t : result) {
            Product product = products.get(i);
            t.setName(product.getName());
            t.setDescription(product.getDescription());
            t.setOrigin(product.getOrigin());
            i++;
        }

        return result;
    }
}
