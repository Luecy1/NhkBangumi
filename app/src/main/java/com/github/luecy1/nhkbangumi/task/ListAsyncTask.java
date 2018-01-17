package com.github.luecy1.nhkbangumi.task;

import android.os.AsyncTask;
import android.util.Log;

import com.github.luecy1.nhkbangumi.Loading;
import com.github.luecy1.nhkbangumi.ProgramListAdapter;
import com.github.luecy1.nhkbangumi.entity.common.Program;
import com.github.luecy1.nhkbangumi.entity.program.ProgramListRoot;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by you on 2017/12/16.
 */

public class ListAsyncTask extends AsyncTask<Integer, Integer, List<Program>> {

    private String url;
    private ProgramListAdapter programListAdapter;
    private Loading loading;

    public ListAsyncTask(String url, ProgramListAdapter programListAdapter, Loading loading) {
        this.url = url;
        this.programListAdapter = programListAdapter;
        this.loading = loading;
    }

    @Override
    protected List<Program> doInBackground(Integer... integers) {

        Request req = new Request
                .Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();

        ProgramListRoot programListRoot = null;

        try {
            Response resp = client.newCall(req).execute();

            Gson gson = new Gson();
            programListRoot = gson.fromJson(resp.body().string(), ProgramListRoot.class);

            resp.body().close();

        } catch (IOException e) {
            Log.e("MyApp", "IOExceptionが発生しました。");
            e.printStackTrace();
        }

        List<Program> programs = new ArrayList<>();

        if (programListRoot == null || programListRoot.getList() == null) {
            return programs;
        }

        // TV
        ifAddNonNull(programs, programListRoot.getList().getG1());
        ifAddNonNull(programs, programListRoot.getList().getG2());
        ifAddNonNull(programs, programListRoot.getList().getE1());
        ifAddNonNull(programs, programListRoot.getList().getE2());
        ifAddNonNull(programs, programListRoot.getList().getE3());
        ifAddNonNull(programs, programListRoot.getList().getE4());
        ifAddNonNull(programs, programListRoot.getList().getS1());
        ifAddNonNull(programs, programListRoot.getList().getS2());
        ifAddNonNull(programs, programListRoot.getList().getS3());
        ifAddNonNull(programs, programListRoot.getList().getS4());

        // radio
        ifAddNonNull(programs, programListRoot.getList().getR1());
        ifAddNonNull(programs, programListRoot.getList().getR2());
        ifAddNonNull(programs, programListRoot.getList().getR3());

        // netradio
        ifAddNonNull(programs, programListRoot.getList().getN1());
        ifAddNonNull(programs, programListRoot.getList().getN2());
        ifAddNonNull(programs, programListRoot.getList().getN3());

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
            programListAdapter.addProgramList(programList);
        }

        loading.close();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        loading.close();
    }
}
