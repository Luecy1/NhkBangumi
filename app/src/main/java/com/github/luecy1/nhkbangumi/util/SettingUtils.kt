package com.github.luecy1.nhkbangumi.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import java.util.HashSet


/**
 * Created by you on 2018/01/06.
 */

object SettingUtils {

    fun initSettings(context: Context) {
        val preference = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preference.edit()

        // サービス
        val serviceSet = HashSet<String>()
        serviceSet.add("tv")
        editor.putStringSet("service", serviceSet)

        // API KEY
        editor.putString("nhk_api_key", "")

        // 地域
        // 初期値は東京(130)
        editor.putString("area", "130")

        editor.apply()
    }
}
