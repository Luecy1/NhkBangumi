package com.github.luecy1.nhkbangumi.task

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.text.util.Linkify
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.github.luecy1.nhkbangumi.Const
import com.github.luecy1.nhkbangumi.Loading
import com.github.luecy1.nhkbangumi.ProgramDetailActivity
import com.github.luecy1.nhkbangumi.R
import com.github.luecy1.nhkbangumi.entity.description.Description
import com.github.luecy1.nhkbangumi.entity.description.DescriptionListRoot
import com.github.luecy1.nhkbangumi.util.CommonUtils
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by you on 2017/12/16.
 */

class DetailAsyncTask(private val url: String, private val context: Context, private val activity: ProgramDetailActivity, private val loading: Loading) : AsyncTask<Void, Void, DescriptionListRoot>() {

    override fun doInBackground(vararg params: Void): DescriptionListRoot? {

        val req = Request.Builder()
                .url(url)
                .build()

        val client = OkHttpClient()

        var descriptionListRoot: DescriptionListRoot? = null

        try {
            val resp = client.newCall(req).execute()

            val resoCode = resp.code()
            if (resoCode != 200) {
                Toast.makeText(context, "サーバーエラー", Toast.LENGTH_LONG).show()
                return null
            }

            val gson = Gson()
            descriptionListRoot = gson.fromJson(resp.body()!!.string(), DescriptionListRoot::class.java)

            resp.body()!!.close()

        } catch (e: IOException) {
            Log.e("MyApp", "IOExceptionが発生しました。")
            e.printStackTrace()
        }

        return descriptionListRoot
    }

    override fun onPostExecute(descriptionListRoot: DescriptionListRoot?) {
        if (descriptionListRoot == null || descriptionListRoot.list == null) {
            return
        }

        var tmpDescription: Description? = null
        if (descriptionListRoot.list!!.g1 != null) {
            tmpDescription = descriptionListRoot.list!!.g1!![0]
        } else if (descriptionListRoot.list!!.g2 != null) {
            tmpDescription = descriptionListRoot.list!!.g2!![0]
        } else if (descriptionListRoot.list!!.e1 != null) {
            tmpDescription = descriptionListRoot.list!!.e1!![0]
        } else if (descriptionListRoot.list!!.e2 != null) {
            tmpDescription = descriptionListRoot.list!!.e2!![0]
        } else if (descriptionListRoot.list!!.e3 != null) {
            tmpDescription = descriptionListRoot.list!!.e3!![0]
        } else if (descriptionListRoot.list!!.e4 != null) {
            tmpDescription = descriptionListRoot.list!!.e4!![0]
        } else if (descriptionListRoot.list!!.s1 != null) {
            tmpDescription = descriptionListRoot.list!!.s1!![0]
        } else if (descriptionListRoot.list!!.s2 != null) {
            tmpDescription = descriptionListRoot.list!!.s2!![0]
        } else if (descriptionListRoot.list!!.s3 != null) {
            tmpDescription = descriptionListRoot.list!!.s3!![0]
        } else if (descriptionListRoot.list!!.s4 != null) {
            tmpDescription = descriptionListRoot.list!!.s4!![0]
        }
        if (tmpDescription == null) {
            return
        }
        val description = tmpDescription

        // 番組ロゴ画像
        val logo = activity.findViewById<ImageView>(R.id.program_detail_logo)
        if (description.program_logo != null) {
            Picasso.with(context).load("http:" + description.program_logo!!.url!!).into(logo)
        } else {
        }

        // 番組タイトル
        val titleView = activity.findViewById<TextView>(R.id.program_detail_title)
        titleView.text = description.title

        // 番組内容
        val subtitleView = activity.findViewById<TextView>(R.id.program_detail_subtitle)
        val subtitle = description.subtitle
        if ("" != subtitle) {
            subtitleView.text = subtitle
        } else {
            subtitleView.text = "番組内容なし"
        }

        // 番組詳細
        val contentView = activity.findViewById<TextView>(R.id.program_detail_content)
        val content = description.content
        if ("" != content) {
            contentView.text = content
        } else {
            contentView.text = "番組詳細なし"
        }

        // 放送地域
        val areaView = activity.findViewById<TextView>(R.id.program_detail_area)
        val area = description.area!!.name
        if ("" != area) {
            areaView.text = area
        } else {
            areaView.text = "放送地域情報なし"
        }

        // 放送サービス
        val serviceView = activity.findViewById<TextView>(R.id.program_detail_service)
        val service = description.service!!.name
        serviceView.text = service

        // 放送時間
        val timeView = activity.findViewById<TextView>(R.id.program_detail_time)
        // 日付変換
        var startDateString = ""
        var endDateString = ""
        try {
            val startDate = CommonUtils.string2Date(description.start_time!!)
            val endDate = CommonUtils.string2Date(description.end_time!!)
            val format = SimpleDateFormat("yyyy年MM月dd日 ah:mm:ss", Locale.JAPAN)
            startDateString = format.format(startDate).toString()
            endDateString = format.format(endDate).toString()
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        timeView.text = "$startDateString～\n$endDateString"

        // 出演者
        val actView = activity.findViewById<TextView>(R.id.program_detail_act)
        actView.text = description.act

        // 番組ジャンル
        val genreView = activity.findViewById<TextView>(R.id.program_detail_genre)
        val genreStrBuilder = StringBuilder()
        for (genre in description.genres!!) {
            if (genreStrBuilder.length != 0) {
                genreStrBuilder.append(",")
            }
            genreStrBuilder.append(Const.GENRE_MAP_CODE[genre])
        }
        genreView.text = genreStrBuilder

        // 番組URL
        val urlView = activity.findViewById<TextView>(R.id.program_detail_program_url)
        urlView.autoLinkMask = Linkify.WEB_URLS
        urlView.text = "http:" + description.program_url!!

        // エピソードURL
        val epiUrlView = activity.findViewById<TextView>(R.id.program_detail_episode_url)
        if (description.episode_url != null && "" != description.episode_url) {
            epiUrlView.text = "http:" + description.episode_url!!
            epiUrlView.autoLinkMask = Linkify.WEB_URLS
        } else {
            epiUrlView.text = "番組URL(放送回)なし"
        }

        // その他の情報
        val extraView = activity.findViewById<TextView>(R.id.program_detail_extras)
        if (description.extras != null) {
            extraView.text = description.extras!!.ondemand_program!!.title
            extraView.text = description.extras!!.ondemand_episode!!.title
        } else {
            extraView.text = "その他の情報なし"
        }

        // Googleカレンダーに登録を有効化
        val calenderButton = activity.findViewById<Button>(R.id.program_detail_calender)
        calenderButton.setOnClickListener {
            val startCal = Calendar.getInstance(Locale.JAPAN)
            val endCal = Calendar.getInstance(Locale.JAPAN)
            try {
                val startDate = CommonUtils.string2Date(description.start_time!!)
                val endDate = CommonUtils.string2Date(description.end_time!!)

                startCal.time = startDate
                endCal.time = endDate
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            val intent = Intent(Intent.ACTION_EDIT)
            intent.type = "vnd.android.cursor.item/event"
            intent.putExtra("title", description.title)
            intent.putExtra("description", description.subtitle)
            intent.putExtra("beginTime", startCal.timeInMillis) //開始日時
            intent.putExtra("endTime", endCal.timeInMillis) //終了日時
            activity.startActivity(intent)
        }

        loading.close()
    }
}
