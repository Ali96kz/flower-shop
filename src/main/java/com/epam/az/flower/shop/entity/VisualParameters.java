package com.epam.az.flower.shop.entity;

public class VisualParameters extends BaseEntity {
    private String colorSteam;
    private String colorLeaves;

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

}
