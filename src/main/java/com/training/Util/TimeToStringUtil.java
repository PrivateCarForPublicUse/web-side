package com.training.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeToStringUtil {
    public static SimpleDateFormat dfToTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat dfToDay = new SimpleDateFormat("yyyy-MM-dd");

    public static String getCurrentTime(){
        return dfToTime.format(new Date());
    }

    public static String getCurrentDay(){
        return dfToDay.format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(getCurrentDay());
    }
}
