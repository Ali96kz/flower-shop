package com.epam.az.pool.entity;

public class WaterInWeek extends BaseEntity{
    private int min;
    private int max;
    public WaterInWeek() {
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return min + " " + max;
    }
}
