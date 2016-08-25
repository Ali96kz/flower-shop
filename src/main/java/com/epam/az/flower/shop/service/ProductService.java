package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.DAO.ProductDAO;
import com.epam.az.flower.shop.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    ProductDAO productDAO = new ProductDAO();
    public List<Product> getAllProduct(){
        List<Product> products = productDAO.getAll();
        return products;
    }
    public void addNewProduct(Product product){

    }

    public Product findById(int id) {
        return productDAO.findById(id);
    }
}
