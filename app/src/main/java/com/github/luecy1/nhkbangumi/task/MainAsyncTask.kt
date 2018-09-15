package com.github.luecy1.nhkbangumi.task

import android.os.AsyncTask
import android.util.Log
import com.github.luecy1.nhkbangumi.Loading
import com.github.luecy1.nhkbangumi.MainActivityListAdapter
import com.github.luecy1.nhkbangumi.entity.nowonair.NowOnAir
import com.github.luecy1.nhkbangumi.entity.nowonair.NowOnAirRoot
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.*

/**
 * Created by you on 2018/01/08.
 */

class MainAsyncTask(private val url: String, private val adapter: MainActivityListAdapter, private val loading: Loading) : AsyncTask<Int, Int, List<NowOnAir>>() {

    override fun doInBackground(vararg params: Int?): List<NowOnAir>? {

        val req = Request.Builder()
                .url(url)
                .build()

        val client = OkHttpClient()

        var json: NowOnAirRoot? = null

        try {
            val resp = client.newCall(req).execute()

            val gson = Gson()
            json = gson.fromJson(resp.body()!!.string(), NowOnAirRoot::class.java)


            resp.body()!!.close()

        } catch (e: IOException) {
            Log.e("MyApp", "IOExceptionが発生しました。")
            e.printStackTrace()
        }

        val programs = ArrayList<NowOnAir>()

        if ((json?.nowOnAirList != null).not()) {
            return programs
        }

        // TV
        ifAddNonNull(programs, json?.nowOnAirList!!.g1)
        ifAddNonNull(programs, json.nowOnAirList!!.g2)
        ifAddNonNull(programs, json.nowOnAirList!!.e1)
        ifAddNonNull(programs, json.nowOnAirList!!.e2)
        ifAddNonNull(programs, json.nowOnAirList!!.e3)
        ifAddNonNull(programs, json.nowOnAirList!!.e4)
        ifAddNonNull(programs, json.nowOnAirList!!.s1)
        ifAddNonNull(programs, json.nowOnAirList!!.s2)
        ifAddNonNull(programs, json.nowOnAirList!!.s3)
        ifAddNonNull(programs, json.nowOnAirList!!.s4)

        // radio
        ifAddNonNull(programs, json.nowOnAirList!!.r1)
        ifAddNonNull(programs, json.nowOnAirList!!.r2)
        ifAddNonNull(programs, json.nowOnAirList!!.r3)

        // netradio
        ifAddNonNull(programs, json.nowOnAirList!!.n1)
        ifAddNonNull(programs, json.nowOnAirList!!.n2)
        ifAddNonNull(programs, json.nowOnAirList!!.n3)

        return programs
    }

    private fun ifAddNonNull(programs: MutableList<NowOnAir>, nowOnAir: NowOnAir?) {
        if (nowOnAir != null) {
            programs.add(nowOnAir)
        }
    }

    override fun onPostExecute(programList: List<NowOnAir>?) {

        if (programList != null) {
            adapter.addProgramList(programList)
        }

        loading.close()
    }
}
