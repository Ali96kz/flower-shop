package com.epam.az.pool;

import com.epam.az.pool.DAO.AbstractDAO;
import com.epam.az.pool.DAO.FlowerTypedDAO;


public class Main {
    public static void main(String[] args) {
        AbstractDAO abstractDAO = new FlowerTypedDAO();
        abstractDAO.getAll();
    }

}
