package com.gt.fjbatresv.devs502.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by javie_000 on 10/17/2016.
 */
public class GeneralUtil {
    public static String getFechaHoy(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public static Date stringDate(String date, String format) throws ParseException {
        if (format == null){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.parse(date);
    }
    public static String uniqueCui(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static long getTime(){
        return System.currentTimeMillis();
    }
}
