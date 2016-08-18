package com.epam.az.pool.service;

import com.epam.az.pool.DAO.AbstractDAO;
import com.epam.az.pool.DAO.FlowerDAO;
import com.epam.az.pool.DAO.ProductDAO;
import com.epam.az.pool.DAO.UnityDAO;
import com.epam.az.pool.entity.Product;

import java.util.List;

public class ProductService{
    AbstractDAO <Product> productDAO = new ProductDAO();
    AbstractDAO <Product> flowerDao =new FlowerDAO();

    public int insert(Product product) {
        int id = productDAO.insert(product);
        product.setId(id);
        flowerDao.insert(product);
        return id;
    }

    public void update(Product product) {
        productDAO.update(product);
        flowerDao.update(product);
    }

    public Product findById(int id, Class aclass) {
        flowerDao.setGenericClass(aclass);
        Product product = productDAO.findById(id);
        Product result = flowerDao.findById(id);
        result.setId(product.getId());
        result.setOrigin(product.getOrigin());
        result.setName(product.getName());
        result.setDescription(product.getDescription());
        result.setType(product.getType());
        result.setDeleteDate(product.getDeleteDate());
        return result;
    }

    public List<Product> getAllProduct() {
        List<Product> products = productDAO.getAll();
        productDAO.setGenericClass(Product.class);
        return products;
    }

    public List<Product> getConcreteListProduct(Class aclass){
        List<Product> products = productDAO.getAll();
        flowerDao.setGenericClass(aclass);
        List<Product> result = flowerDao.getAll();
        int i = 0;

        for (Product t : result) {
            t .setName(products.get(i).getName());
            t.setDescription(products.get(i).getDescription());
            t.setOrigin(products.get(i).getOrigin());
        }
        return result;
    }

}
