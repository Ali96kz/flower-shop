package com.epam.az.flower.shop.entity;


import java.sql.Date;

public class Product extends BaseEntity{
    private Integer price;
    private Origin origin;
    private String type;
    private Flower flower;
    private String description;

    public Product(){

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }
}