package com.epam.az.flower.shop.entity;


import java.sql.Date;

public class UserOrder extends BaseEntity{
    private User user;
    private Product product;
    private Date orderDate;
    public UserOrder(){
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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
