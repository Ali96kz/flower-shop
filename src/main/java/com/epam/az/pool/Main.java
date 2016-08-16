package com.epam.az.pool;

import com.epam.az.pool.DAO.AbstractDAO;
import com.epam.az.pool.DAO.OriginDAO;
import com.epam.az.pool.entity.Origin;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AbstractDAO abstractDAO = new OriginDAO();
        Origin origin = new Origin();
        origin.setCountry("sss");
        origin.setProvince("asdasd");
        origin.setId(1);
//        abstractDAO.update(origin);
//        abstractDAO.delete(origin);
        List<Origin> list =  abstractDAO.getAll();
        System.out.println(list.size());
        for (Origin origin1 : list) {
            System.out.println(origin1.getId()+" "+origin1.getCountry());
        }
    }
}
