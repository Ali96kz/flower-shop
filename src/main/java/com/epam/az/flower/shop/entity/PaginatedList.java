package com.epam.az.flower.shop.entity;

import java.util.ArrayList;
import java.util.List;

public class PaginatedList {
    public List<Product> productList;
    private int pageNumber;
    private int pageSize;

    public PaginatedList(int pageSize, List<Product> products) {
        this.pageSize = pageSize;
        this.productList = products;

    }

    public List<Product> getPage(int id) {
        List<Product> result = new ArrayList<>();

        for (int i = id * pageSize; i < id * pageSize + pageSize; i++) {
            if(productList.size() <= i) {
                break;
            }
            result.add(productList.get(i));
        }
        return result;
    }



    public int getPageNumber() {
        if (productList.size() % pageSize== 0) {
            return productList.size() / pageSize;
        }
        return productList.size() / pageSize;
    }
}
