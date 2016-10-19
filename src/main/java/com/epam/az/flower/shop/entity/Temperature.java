package com.epam.az.flower.shop.entity;


public class Temperature extends BaseEntity {
    private int tmin;
    private int tmax;

    public Temperature() {

    }

    public int getTmin() {
        return tmin;
    }

    public void setTmin(int tmin) {
        this.tmin = tmin;
    }

    public int getTmax() {
        return tmax;
    }

    public void setTmax(int tmax) {
        this.tmax = tmax;
    }

    @Override
    public String toString() {
        return tmin + " " + tmax;
    }
}
