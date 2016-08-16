package com.epam.az.pool.entity;

import javax.xml.bind.annotation.XmlElement;

public class Temperature {
    private int min;
    private int max;

    public Temperature() {

    }

    public int getMin() {
        return min;
    }

    @XmlElement
    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    @XmlElement
    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return min + " " + max;
    }
}
