package com.epam.az.flower.shop.service;

import com.epam.az.flower.shop.DAO.ProductDAO;
import com.epam.az.flower.shop.entity.Product;
import com.epam.az.flower.shop.entity.ProductPagination;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    ProductDAO productDAO = new ProductDAO();
    public List<Product> getAllProduct(){
        List<Product> products = productDAO.getAll();
        return products;
    }
    public ProductPagination getPagination(){
        List<Product> products = getAllProduct();
        ProductPagination pagination = new ProductPagination();
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if((i+1) % 10 == 0){
                list.add(products.get(i));
                pagination.addProducts(list);
                list = new ArrayList<>();
            }
            list.add(products.get(i));
        }
        return pagination;
    }
    public void addNewProduct(Product product){

    }

    public Product findById(int id) {
        return productDAO.findById(id);
    }
}
