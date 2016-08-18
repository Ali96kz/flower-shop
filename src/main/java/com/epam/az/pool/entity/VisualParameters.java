package com.epam.az.pool.entity;

public class VisualParameters extends BaseEntity{
    private String colorSteam;
    private String colorLeaves;
    private int averageHeight;


    public VisualParameters() {
    }

    public String getColorSteam() {
        return colorSteam;
    }

    public void setColorSteam(String colorSteam) {
        this.colorSteam = colorSteam;
    }

    public String getColorLeaves() {
        return colorLeaves;
    }

    public void setColorLeaves(String colorLeaves) {
        this.colorLeaves = colorLeaves;
    }

    public int getAverageHeight() {
        return averageHeight;
    }

    public void setAverageHeight(int averageHeight) {
        this.averageHeight = averageHeight;
    }

    @Override
    public String toString() {
        return colorLeaves + " " + colorSteam + " " + averageHeight;
    }
}
