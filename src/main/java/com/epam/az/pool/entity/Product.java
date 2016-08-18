package com.epam.az.pool.entity;

public abstract class Product extends BaseEntity{
    private int price;
    private Origin origin;

    public Origin getOrigin() {
        return this.origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
