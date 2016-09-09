package com.epam.az.flower.shop.entity;

import java.util.ArrayList;
import java.util.List;

public class ProductPagination {
    private List<ProductList> productPagination = new ArrayList<>();
    public void addProducts(ProductList productList){
        productPagination.add(productList);
    }

    public ProductList getProductList(int id) {
        return productPagination.get(id);
    }

    public int listSize(){
        return productPagination.size();
    }
}
