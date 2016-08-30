package com.epam.az.flower.shop.adapter;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringAdapter {
    public Date toSqlDate(String date){
        if(date == null)
            return null;
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date sqlDate = null;
        try {
            sqlDate = new Date(df.parse(date).getTime());
            return sqlDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }
    public int toInt(String s){
        return Integer.parseInt(s);
    }
}
