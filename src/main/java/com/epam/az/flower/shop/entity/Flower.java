package com.epam.az.flower.shop.entity;


public  class Flower extends BaseEntity{
    private String name;


    private VisualParameters visualParameters;
    private GrowingCondition growingCondition;

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
