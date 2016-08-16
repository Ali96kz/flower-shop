package com.epam.az.pool;


import com.epam.az.pool.DAO.AbstractDAO;
import com.epam.az.pool.DAO.OriginDAO;
import com.epam.az.pool.entity.Origin;

public class Main {
    public static void main(String[] args) {
        AbstractDAO abstractDAO = new OriginDAO();
        Origin origin =new Origin();
        origin.setId(45);
        origin.setProvince("asd");
        origin.setCountry("asdasd");
        abstractDAO.update(origin);
        abstractDAO.insert(origin);

    }
}
