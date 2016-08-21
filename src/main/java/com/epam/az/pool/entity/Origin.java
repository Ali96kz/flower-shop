package com.epam.az.pool.entity;

import com.epam.az.pool.annotation.Column;
import com.epam.az.pool.annotation.Table;

public class Origin extends BaseEntity{
    @Column(name = "country")
    private String country;
    @Column(name = "province")
    private String province;
    @Table(name = "Origin")
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

