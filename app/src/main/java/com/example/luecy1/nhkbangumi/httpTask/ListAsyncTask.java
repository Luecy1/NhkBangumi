package com.example.luecy1.nhkbangumi.httpTask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.luecy1.nhkbangumi.Loading;
import com.example.luecy1.nhkbangumi.ProgramListAdapter;
import com.example.luecy1.nhkbangumi.entity.common.Program;
import com.example.luecy1.nhkbangumi.entity.program.ProgramList;
import com.fasterxml.jackson.databind.ObjectMapper;

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

        // TV
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

        // radio
        ifAddNonNull(programs, programList.getList().getR1());
        ifAddNonNull(programs, programList.getList().getR2());
        ifAddNonNull(programs, programList.getList().getR3());

        // netradio
        ifAddNonNull(programs, programList.getList().getN1());
        ifAddNonNull(programs, programList.getList().getN2());
        ifAddNonNull(programs, programList.getList().getN3());

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
