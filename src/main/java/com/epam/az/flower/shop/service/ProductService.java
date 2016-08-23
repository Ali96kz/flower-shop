package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.DAO.ProductDAO;
import com.epam.az.flower.shop.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    ProductDAO productDAO = new ProductDAO();
    public List<Product> getAllProduct(){
//        List<Product> products = productDAO.getAll();
        List<Product> products =  new ArrayList<>();
        Product product = new Product();
        product.setId(45);
        products.add(product);

        product = new Product();
        product.setId(26);
        products.add(product);
        return products;
    }
    public void addNewProduct(Product product){

    }
}
