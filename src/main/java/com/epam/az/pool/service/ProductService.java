package com.epam.az.pool.service;

import com.epam.az.pool.DAO.AbstractDAO;
import com.epam.az.pool.DAO.FlowerDAO;
import com.epam.az.pool.DAO.ProductDAO;
import com.epam.az.pool.DAO.UnityDAO;
import com.epam.az.pool.entity.Product;

import java.util.List;

public class ProductService<T extends Product> {
    AbstractDAO <Product> productDAO = new ProductDAO();
    AbstractDAO <T> flowerDao =new FlowerDAO();
    public void insert(Product product) {
        int id = productDAO.insert(product);
        product.setId(id);
        flowerDao.insert((T) product);
    }

    public void update(Product product) {
        productDAO.update(product);
        flowerDao.update((T) product);
    }

    public T findById(int id) {
        Product product = productDAO.findById(id);
        UnityDAO unityDAO = new UnityDAO(product.getClass());
        T result = (T) unityDAO.findById(id);
        result.setName(product.getName());
        return result;
    }

    public List<Product> getAllProduct() {
        List<Product> products = productDAO.getAll();
        return products;
    }
    public List<T> getConcreteListProduct(Class aclass){
        List<Product> products = productDAO.getAll();
        flowerDao.setGenericClass(aclass);
        List<T> result = flowerDao.getAll();
        int i = 0;
        for (T t : result) {
            t .setName(products.get(i).getName());
            t.setDescription(products.get(i).getDescription());
            t.setOrigin(products.get(i).getOrigin());
        }
        return result;
    }

}
