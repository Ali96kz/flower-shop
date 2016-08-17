package com.epam.az.pool.entity;

import java.util.Date;

public class User extends BaseEntity {
    private String NickName;
    private String lastname;
    private Date dateBirhday;

    public User() {

    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
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
