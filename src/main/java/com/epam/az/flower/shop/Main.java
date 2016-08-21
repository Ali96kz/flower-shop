package com.epam.az.flower.shop;

import com.epam.az.flower.shop.DAO.AbstractDAO;
import com.epam.az.flower.shop.DAO.FlowerTypedDAO;


public class Main {
    public static void main(String[] args) {
        AbstractDAO abstractDAO = new FlowerTypedDAO();
        abstractDAO.getAll();
    }

}
