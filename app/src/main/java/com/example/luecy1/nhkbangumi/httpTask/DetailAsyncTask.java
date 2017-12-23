package com.example.luecy1.nhkbangumi.httpTask;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luecy1.nhkbangumi.ProgramDetailActivity;
import com.example.luecy1.nhkbangumi.R;
import com.example.luecy1.nhkbangumi.entity.description.Description;
import com.example.luecy1.nhkbangumi.entity.description.DescriptionList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by you on 2017/12/16.
 */

public class DetailAsyncTask extends AsyncTask<Void, Void, DescriptionList> {

    private String url;
    private Context context;
    private ProgramDetailActivity activity;

    public DetailAsyncTask(String url, Context context, ProgramDetailActivity activity) {
        this.url = url;
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected DescriptionList doInBackground(Void... params) {


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

        DescriptionList descriptionList = null;

        try {
            Response resp = client.newCall(req).execute();

            ObjectMapper mapper = new ObjectMapper();
            descriptionList = mapper.readValue(resp.body().string(), DescriptionList.class);

            resp.body().close();

        } catch (IOException e) {
            Log.e("MyApp", "IOExceptionが発生しました。");
            e.printStackTrace();
        }

        return descriptionList;
    }

    @Override
    protected void onPostExecute(DescriptionList descriptionList) {

        Description description = descriptionList.getList().getG1().get(0);

        ImageView logo = activity.findViewById(R.id.program_detail_logo);
        Picasso.with(context).load("https:" + description.getProgram_logo().getUrl()).into(logo);

        TextView titleView = activity.findViewById(R.id.program_detail_title);
        titleView.setText(description.getTitle());

        TextView contentView = activity.findViewById(R.id.program_detail_content);
        contentView.setText(description.getContent());

    }
}
