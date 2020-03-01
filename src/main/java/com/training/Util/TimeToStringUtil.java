package com.training.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeToStringUtil {
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String getCurrentTime(){
        return df.format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(getCurrentTime());
    }
}
