package com.example.luecy1.nhkbangumi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by you on 2017/12/23.
 */

public class CommonUtils {

    private static final SimpleDateFormat NHK_DATE_FORMAT = new SimpleDateFormat("YYYY-MM-DD HH:mm:ssZ") ;

    // no instance
    private CommonUtils() {
    }

    synchronized public static Date string2Date(String dateString) throws ParseException {
        dateString = dateString.replace("T"," ");
        return NHK_DATE_FORMAT.parse(dateString);
    }
}
