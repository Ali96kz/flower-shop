package com.epam.az.flower.shop.entity;

import java.util.ArrayList;
import java.util.List;

public class ProductPagination {
    private List<List<Product>> productPagination = new ArrayList<>();

    public void addProducts(List<Product> products){
        productPagination.add(products);
    }

    public List<Product> get(int i){
        return productPagination.get(i);
    }
    public int listSize(){
        return productPagination.size();
    }
}
