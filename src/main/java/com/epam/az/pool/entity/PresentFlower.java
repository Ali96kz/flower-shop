package com.epam.az.pool.entity;

public abstract class PresentFlower extends Flower {
    private int height;
    private VisualParameters visualParameters;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }
}
