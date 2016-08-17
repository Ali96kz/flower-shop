package com.epam.az.pool.entity;

public class Order extends BaseEntity{
    private User user;
    private BaseEntity baseEntity;

    public Order(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BaseEntity getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(BaseEntity baseEntity) {
        this.baseEntity = baseEntity;
    }
}
