package com.epam.az.flower.shop.entity;


public class Flower extends BaseEntity {
    private String name;
    private int averageHeight;
    private VisualParameters visualParameters;
    private GrowingCondition growingCondition;
    private FlowerType flowerType;

    public int getAverageHeight() {
        return averageHeight;
    }

    public void setAverageHeight(int averageHeight) {
        this.averageHeight = averageHeight;
    }

    public FlowerType getFlowerType() {
        return flowerType;
    }

    public void setFlowerType(FlowerType flowerType) {
        this.flowerType = flowerType;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public GrowingCondition getGrowingCondition() {
        return growingCondition;
    }

    public void setGrowingCondition(GrowingCondition growingCondition) {
        this.growingCondition = growingCondition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
