package com.epam.az.pool.service;

import com.epam.az.pool.DAO.AbstractDAO;
import com.epam.az.pool.DAO.ProductDAO;
import com.epam.az.pool.DAO.UnityDAO;
import com.epam.az.pool.entity.Product;

import java.util.List;

public class ProductService<T extends Product> {
    AbstractDAO<Product> abstractDAO = new ProductDAO();

    public void insert(Product product) {
        abstractDAO.insert(product);
        int id = 152;//TODO get This id from product table
        product.setId(id);
        UnityDAO unityDAO = new UnityDAO(product.getClass());
        unityDAO.insert(product);
    }

    public void update(Product product) {
        abstractDAO.update(product);
        int id = 152;//TODO get This id from product table
        UnityDAO unityDAO = new UnityDAO(product.getClass());
        unityDAO.insert(product);
    }

    public T findById(int id) {
        //TODO JOIN
        Product product = abstractDAO.findById(id);
        UnityDAO unityDAO = new UnityDAO(product.getClass());
        T result = (T) unityDAO.findById(id);
        result.setName(product.getName());
        return result;
    }

    public List<T> getAll(Class productClass) {
        UnityDAO unityDAO = new UnityDAO(productClass);
        List<T> products = (List<T>) abstractDAO.getAll();
        List<T> result = unityDAO.getAll();
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
