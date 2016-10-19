package com.epam.az.flower.shop.dao;

import com.epam.az.flower.shop.entity.BaseEntity;

public class DAOException extends Exception {
    public DAOException(String msg) {
        super(msg);
    }

    public <E extends BaseEntity> DAOException(String msg, Exception e) {
        super(msg, e);
    }

}
