package com.github.luecy1.nhkbangumi.task

import android.os.AsyncTask
import android.util.Log
import com.github.luecy1.nhkbangumi.Loading
import com.github.luecy1.nhkbangumi.ProgramListAdapter
import com.github.luecy1.nhkbangumi.entity.common.Program
import com.github.luecy1.nhkbangumi.entity.program.ProgramListRoot
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.*

/**
 * Created by you on 2017/12/16.
 */

class ListAsyncTask(private val url: String, private val programListAdapter: ProgramListAdapter, private val loading: Loading) : AsyncTask<Int, Int, List<Program>>() {

    override fun doInBackground(vararg params: Int?): List<Program>? {

        val req = Request.Builder()
                .url(url)
                .build()

        val client = OkHttpClient()

        var programListRoot: ProgramListRoot? = null

        try {
            val resp = client.newCall(req).execute()

            val gson = Gson()
            programListRoot = gson.fromJson(resp.body()!!.string(), ProgramListRoot::class.java)

            resp.body()!!.close()

        } catch (e: IOException) {
            Log.e("MyApp", "IOExceptionが発生しました。")
            e.printStackTrace()
        }

        val programs = ArrayList<Program>()

        if (programListRoot == null || programListRoot.list == null) {
            return programs
        }

        // TV
        ifAddNonNull(programs, programListRoot.list!!.g1)
        ifAddNonNull(programs, programListRoot.list!!.g2)
        ifAddNonNull(programs, programListRoot.list!!.e1)
        ifAddNonNull(programs, programListRoot.list!!.e2)
        ifAddNonNull(programs, programListRoot.list!!.e3)
        ifAddNonNull(programs, programListRoot.list!!.e4)
        ifAddNonNull(programs, programListRoot.list!!.s1)
        ifAddNonNull(programs, programListRoot.list!!.s2)
        ifAddNonNull(programs, programListRoot.list!!.s3)
        ifAddNonNull(programs, programListRoot.list!!.s4)

        // radio
        ifAddNonNull(programs, programListRoot.list!!.r1)
        ifAddNonNull(programs, programListRoot.list!!.r2)
        ifAddNonNull(programs, programListRoot.list!!.r3)

        // netradio
        ifAddNonNull(programs, programListRoot.list!!.n1)
        ifAddNonNull(programs, programListRoot.list!!.n2)
        ifAddNonNull(programs, programListRoot.list!!.n3)

        return programs
    }

    private fun ifAddNonNull(programs: MutableList<Program>, addProgram: List<Program>?) {
        if (addProgram != null) {
            programs.addAll(addProgram)
        }
    }

    override fun onPostExecute(programList: List<Program>?) {

        if (programList != null) {
            programListAdapter.addProgramList(programList)
        }

        loading.close()
    }

    override fun onCancelled() {
        super.onCancelled()
        loading.close()
    }
}
