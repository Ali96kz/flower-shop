package com.epam.az.pool.entity;

public abstract class Flower extends BaseEntity {
    private String soil;

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }
}
