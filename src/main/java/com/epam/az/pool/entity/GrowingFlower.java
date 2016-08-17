package com.epam.az.pool.entity;

public  class GrowingFlower extends Flower{
    private GrowingTips growingTips;
    private String type;
    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

 }
