package com.epam.az.flower.shop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringAdapter {
    private static Logger logger = LoggerFactory.getLogger(StringAdapter.class);

    public Date toSqlDate(String date) throws UtilClassException {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date sqlDate = new Date(df.parse(date).getTime());
            return sqlDate;

        } catch (ParseException e) {
            logger.error("can't parse date", e);
            throw new UtilClassException("can't parse date", e);
        }
    }

    public int toInt(String s) {
        return Integer.parseInt(s);
    }
}
