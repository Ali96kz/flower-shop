package com.epam.az.flower.shop.entity;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<Product> products = new ArrayList<>();
    private int sum;
    public void add(Product product) {
        sum += product.getPrice();
        products.add(product);
    }

    public int getSum() {
        return sum;
    }

    public void delete(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                sum -= products.get(i).getPrice();
                products.remove(i);

                return;
            }
          }
    }

    public List<Product> getProducts() {
        return products;
    }
}
