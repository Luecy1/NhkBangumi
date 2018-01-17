package com.github.luecy1.nhkbangumi.task;

import android.os.AsyncTask;
import android.util.Log;

import com.github.luecy1.nhkbangumi.Loading;
import com.github.luecy1.nhkbangumi.MainActivityListAdapter;
import com.github.luecy1.nhkbangumi.entity.nowonair.NowOnAir;
import com.github.luecy1.nhkbangumi.entity.nowonair.NowOnAirRoot;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by you on 2018/01/08.
 */

public class MainAsyncTask extends AsyncTask<Integer, Integer, List<NowOnAir>> {

    private String url;
    private MainActivityListAdapter adapter;
    private Loading loading;

    public MainAsyncTask(String url, MainActivityListAdapter adapter, Loading loading) {
        this.url = url;
        this.adapter = adapter;
        this.loading = loading;
    }

    @Override
    protected List<NowOnAir> doInBackground(Integer... integers) {

        Request req = new Request
                .Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();

        NowOnAirRoot json = null;

        try {
            Response resp = client.newCall(req).execute();

            Gson gson = new Gson();
            json = gson.fromJson(resp.body().string(), NowOnAirRoot.class);


            resp.body().close();

        } catch (IOException e) {
            Log.e("MyApp", "IOExceptionが発生しました。");
            e.printStackTrace();
        }

        List<NowOnAir> programs = new ArrayList<>();

        if (json == null || json.getNowOnAirList() == null) {
            return programs;
        }

        // TV
        ifAddNonNull(programs, json.getNowOnAirList().getG1());
        ifAddNonNull(programs, json.getNowOnAirList().getG2());
        ifAddNonNull(programs, json.getNowOnAirList().getE1());
        ifAddNonNull(programs, json.getNowOnAirList().getE2());
        ifAddNonNull(programs, json.getNowOnAirList().getE3());
        ifAddNonNull(programs, json.getNowOnAirList().getE4());
        ifAddNonNull(programs, json.getNowOnAirList().getS1());
        ifAddNonNull(programs, json.getNowOnAirList().getS2());
        ifAddNonNull(programs, json.getNowOnAirList().getS3());
        ifAddNonNull(programs, json.getNowOnAirList().getS4());

        // radio
        ifAddNonNull(programs, json.getNowOnAirList().getR1());
        ifAddNonNull(programs, json.getNowOnAirList().getR2());
        ifAddNonNull(programs, json.getNowOnAirList().getR3());

        // netradio
        ifAddNonNull(programs, json.getNowOnAirList().getN1());
        ifAddNonNull(programs, json.getNowOnAirList().getN2());
        ifAddNonNull(programs, json.getNowOnAirList().getN3());

        return programs;
    }

    private void ifAddNonNull(List<NowOnAir> programs, NowOnAir nowOnAir) {
        if (nowOnAir != null) {
            programs.add(nowOnAir);
        }
    }

    @Override
    protected void onPostExecute(List<NowOnAir> programList) {

        if (programList != null) {
            adapter.addProgramList(programList);
        }

        loading.close();
    }
}
