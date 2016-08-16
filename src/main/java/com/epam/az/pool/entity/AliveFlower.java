package com.epam.az.pool.entity;

public class AliveFlower extends PresentFlower {
    private int aliveDay;
    private GrowingTips growingTips;
    private int quantity;

    public AliveFlower() {
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;

    }

    public Object getFlower() {
        return new Object();
    }

    public void setFlower(Object flower) {

    }

    public int getAliveDay() {
        return aliveDay;
    }

    public void setAliveDay(int aliveDay) {
        this.aliveDay = aliveDay;
    }
}
