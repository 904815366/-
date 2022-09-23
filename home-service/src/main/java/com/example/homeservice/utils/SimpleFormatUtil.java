package com.example.homeservice.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleFormatUtil {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static Date stringForDate(String s){
        try {
            Date parse = format.parse(s);
            return parse;
        }catch (Exception e){
            return null;
        }
    }

    public static String dateForString(Long l){
        try {
            String format = SimpleFormatUtil.format.format(l);
            return format;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("dateForString时间转换失败");
            return null;
        }
    }


}
