package com.epam.az.flower.shop.entity;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    public List<Product> products  = new ArrayList<>();

    public void add(Product product){
        products.add(product);
    }
    public void delete(int i){

    }
    public List<Product> getProducts() {
        return products;
    }
}
