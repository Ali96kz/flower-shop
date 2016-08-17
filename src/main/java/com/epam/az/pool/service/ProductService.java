package com.epam.az.pool.service;

import com.epam.az.pool.DAO.AbstractDAO;
import com.epam.az.pool.DAO.ProductDAO;
import com.epam.az.pool.DAO.UnityDAO;
import com.epam.az.pool.entity.Product;

public class ProductService<T extends Product>{
    public void insert(Product product){
        AbstractDAO<Product> abstractDAO = new ProductDAO();
        abstractDAO.insert(product);
        int id = 152;
        product.setId(id);
        UnityDAO unityDAO = new UnityDAO(product.getClass());
        unityDAO.insert(product);
    }
    public void update(){

    }

}
