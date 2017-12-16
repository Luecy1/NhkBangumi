package com.example.luecy1.nhkbangumi.httpTask;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import com.example.luecy1.nhkbangumi.ProgramListAdapter;
import com.example.luecy1.nhkbangumi.entity.ProgramList;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by you on 2017/12/16.
 */

public class MyAsyncTask extends AsyncTask<Integer, Integer, ProgramList> {

    private String url;
    private ProgramListAdapter programListAdapter;
    private Context context;

    public MyAsyncTask(String url, ProgramListAdapter programListAdapter, Context context) {
        this.url = url;
        this.programListAdapter = programListAdapter;
        this.context = context;
    }

    @Override
    protected ProgramList doInBackground(Integer... integers) {

        //APIキーの取得
        String key = "";
        try {
            ApplicationInfo info
                    = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            key = info.metaData.getString("nhkApiKey");
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("MyApp", "NameNotFoundException");
            e.printStackTrace();
        }

        url = url + "?key=" + key;

        Request req = new Request
                .Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();

        ProgramList programList = null;

        try {
            Response resp = client.newCall(req).execute();

            ObjectMapper mapper = new ObjectMapper();
            programList = mapper.readValue(resp.body().string(), ProgramList.class);

            resp.body().close();

        } catch (IOException e) {
            Log.e("MyApp", "IOExceptionが発生しました。");
            e.printStackTrace();
        }

        return programList;
    }

    @Override
    protected void onPostExecute(ProgramList programList) {

        if (programList == null) {
            return;
        }

        programListAdapter.setProgramList(programList);
        Log.d("MyApp", "情報を取得しました。");


    }
}
