package com.epam.az.flower.shop.entity;


public class Product extends BaseEntity{
    private int price;
    private int quantity;
    private Origin origin;
    private Flower flower;
    private String description;

    public Product(){

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }
}
