package com.github.luecy1.nhkbangumi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by you on 2018/01/06.
 */

public class SettingUtils {

    public static void initSettings(Context context) {
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preference.edit();

        // サービス
        Set<String> serviceSet = new HashSet<>();
        serviceSet.add("tv");
        editor.putStringSet("service", serviceSet);

        // API KEY
        editor.putString("nhk_api_key","");

        // 地域
        // 初期値は東京(130)
        editor.putString("area", "130");

        editor.apply();
    }
}
