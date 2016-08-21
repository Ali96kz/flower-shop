package com.epam.az.pool.entity;


public class Order extends BaseEntity{
    private User user;
    private Product product;

    public Order(){
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
