package com.epam.az.pool.entity;

import java.util.Date;

public class User {
    private Integer id;
    private String NickName;
    private String name;
    private String lastname;
    private Date dateBirhday;
    public User(){

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateBirhday() {
        return dateBirhday;
    }

    public void setDateBirhday(Date dateBirhday) {
        this.dateBirhday = dateBirhday;
    }
}
