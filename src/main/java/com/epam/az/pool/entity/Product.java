package com.epam.az.pool.entity;

public abstract class Product extends BaseEntity{
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
