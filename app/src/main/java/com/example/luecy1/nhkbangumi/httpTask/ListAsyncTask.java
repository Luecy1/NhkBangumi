package com.example.luecy1.nhkbangumi.httpTask;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.luecy1.nhkbangumi.Loading;
import com.example.luecy1.nhkbangumi.ProgramListAdapter;
import com.example.luecy1.nhkbangumi.entity.common.Program;
import com.example.luecy1.nhkbangumi.entity.program.ProgramList;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by you on 2017/12/16.
 */

public class ListAsyncTask extends AsyncTask<Integer, Integer, List<Program>> {

    private String url;
    private ProgramListAdapter programListAdapter;
    private Context context;
    private Loading loading;

    public ListAsyncTask(String url, ProgramListAdapter programListAdapter, Context context, Loading loading) {
        this.url = url;
        this.programListAdapter = programListAdapter;
        this.context = context;
        this.loading = loading;
    }

    @Override
    protected List<Program> doInBackground(Integer... integers) {

        // 設定から地域を取得
        String area = PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString("area", "130");
        this.url = this.url + area + "/";

        // 設定からserviceを取得
        String service = PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString("service", "tv");
        this.url = this.url + service + "/";

        // 現在日付の取得
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN);

        // 日本日付
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.HOUR, 9);
        nowDate = calendar.getTime();


        String jsonName = simpleDateFormat.format(nowDate) + ".json";
        url += jsonName;

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

        List<Program> programs = new ArrayList<>();

        if (programList == null || programList.getList() == null) {
            return programs;
        }

        ifAddNonNull(programs, programList.getList().getG1());
        ifAddNonNull(programs, programList.getList().getG2());
        ifAddNonNull(programs, programList.getList().getE1());
        ifAddNonNull(programs, programList.getList().getE2());
        ifAddNonNull(programs, programList.getList().getE3());
        ifAddNonNull(programs, programList.getList().getE4());
        ifAddNonNull(programs, programList.getList().getS1());
        ifAddNonNull(programs, programList.getList().getS2());
        ifAddNonNull(programs, programList.getList().getS3());
        ifAddNonNull(programs, programList.getList().getS4());

        return programs;
    }

    private void ifAddNonNull(List<Program> programs,List<Program> addProgram) {
        if (addProgram != null) {
            programs.addAll(addProgram);
        }
    }

    @Override
    protected void onPostExecute(List<Program> programList) {

        if (programList != null) {
            programListAdapter.setProgramList(programList);
        }

        loading.close();
    }
}
