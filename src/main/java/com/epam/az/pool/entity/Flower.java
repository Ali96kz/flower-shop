package com.epam.az.pool.entity;

public abstract class Flower extends Product {
    private VisualParameters visualParameters;

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }
}
