package com.epam.az.flower.shop.entity;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    List<Product> productList = new ArrayList<>(10);
    public void add(Product product){
        productList.add(product);
    }
    public List<Product> products(){
        return productList;
    }
}
