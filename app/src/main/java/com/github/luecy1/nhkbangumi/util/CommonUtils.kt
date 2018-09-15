package com.github.luecy1.nhkbangumi.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by you on 2017/12/23.
 */

object CommonUtils {

    @Throws(ParseException::class)
    fun string2Date(dateString: String): Date {
        var dateString = dateString
        val nhkDateFormat = SimpleDateFormat("yyyy-MM-DD HH:mm:ssZ", Locale.JAPAN)

        dateString = dateString.replace("T", " ")
        return nhkDateFormat.parse(dateString)
    }

    fun date2String(date: Date): String {
        val format = SimpleDateFormat("yyyy年MM月dd日 ah:mm:ss", Locale.JAPAN)
        return format.format(date).toString()
    }

    fun netWorkCheck(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo
        return info?.isConnected ?: false
    }
}
