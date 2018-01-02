package com.example.luecy1.nhkbangumi.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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

    public static boolean netWorkCheck(Context context){
        ConnectivityManager cm =  (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if( info != null ){
            return info.isConnected();
        } else {
            return false;
        }
    }
}
