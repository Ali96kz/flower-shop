package com.epam.az.flower.shop.entity;


public class Origin extends BaseEntity{
    private String country;
    private String province;
    public Origin() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return country + " " + province;
    }
}

