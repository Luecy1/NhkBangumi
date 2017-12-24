package com.example.luecy1.nhkbangumi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by you on 2017/12/23.
 */

public class CommonUtils {

    // no instance
    private CommonUtils() {
    }

    public static Date string2Date(String dateString) throws ParseException {
        final SimpleDateFormat nhkDateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ssZ", Locale.JAPAN);

        dateString = dateString.replace("T"," ");
        return nhkDateFormat.parse(dateString);
    }
}
