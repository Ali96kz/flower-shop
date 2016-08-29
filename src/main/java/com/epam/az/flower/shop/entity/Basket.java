package com.epam.az.flower.shop.entity;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    public List<Product> products = new ArrayList<>();

    public void add(Product product) {
        products.add(product);
    }

    public void delete(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return;
            }
          }
    }

    public List<Product> getProducts() {
        return products;
    }
}
