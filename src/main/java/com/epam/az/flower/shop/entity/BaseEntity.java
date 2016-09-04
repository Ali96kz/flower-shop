package com.epam.az.flower.shop.entity;


import java.sql.Date;

public abstract class BaseEntity {
    private Integer id;
    private Date deleteDay;

    public Date getDeleteDay() {
        return deleteDay;
    }

    public void setDeleteDay(Date deleteDay) {
        this.deleteDay = deleteDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
