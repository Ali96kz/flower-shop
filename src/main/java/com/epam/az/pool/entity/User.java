package com.epam.az.pool.entity;


import java.sql.Date;

public class User extends BaseEntity {
    private String nickName;
    private String lastname;
    private String firstname;
    private Date dateBirhday;
    private int balance;

    public User() {

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
