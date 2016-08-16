package com.epam.az.pool.entity;

public class GrowingTips {
    private WaterInWeek waterInWeek;
    private Temperature temperature;
    private boolean lovelight;

    public GrowingTips() {
    }

    public WaterInWeek getWaterInWeek() {
        return waterInWeek;
    }

    public void setWaterInWeek(WaterInWeek waterInWeek) {
        this.waterInWeek = waterInWeek;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public boolean getLovelight() {
        return lovelight;
    }

    public void setLovelight(boolean lovelight) {
        this.lovelight = lovelight;
    }

    @Override
    public String toString() {
        return temperature + " " + waterInWeek + " " + lovelight;
    }
}
