package com.epam.az.flower.shop.entity;

import java.util.ArrayList;
import java.util.List;

public class PaginatedList {
    public List<Product> productList = new ArrayList<>();
    private int pageSize;

    public PaginatedList(int pageSize, List<Product> products) {
        this.pageSize = pageSize;
        this.productList = products;

    }

    public PaginatedList(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Product> getPage(int id) {
        List<Product> result = new ArrayList<>();

        for (int i = id * pageSize; i < id * pageSize + pageSize; i++) {
            if (productList.size() <= i) {
                break;
            }
            result.add(productList.get(i));
        }
        return result;
    }

    public void addProduct(Product product){
        productList.add(product);
    }

    public int getPageNumber() {
        int size = productList.size();
        int result = 0;
        if (productList.size() % pageSize == 0) {
            return productList.size() / pageSize;
        }

        while(size > 0){
            size -= pageSize;
            result++;
        }
        return result;
    }
}
