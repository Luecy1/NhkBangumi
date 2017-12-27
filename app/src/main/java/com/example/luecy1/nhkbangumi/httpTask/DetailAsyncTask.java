package com.example.luecy1.nhkbangumi.httpTask;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.text.util.Linkify;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luecy1.nhkbangumi.Loading;
import com.example.luecy1.nhkbangumi.ProgramDetailActivity;
import com.example.luecy1.nhkbangumi.R;
import com.example.luecy1.nhkbangumi.entity.description.Description;
import com.example.luecy1.nhkbangumi.entity.description.DescriptionList;
import com.example.luecy1.nhkbangumi.util.CommonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    private Loading loading;

    public DetailAsyncTask(String url, Context context, ProgramDetailActivity activity, Loading loading) {
        this.url = url;
        this.context = context;
        this.activity = activity;
        this.loading = loading;
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

        TextView subtitleView = activity.findViewById(R.id.program_detail_subtitle);
        String subtitle = description.getSubtitle();
        if (!"".equals(subtitle)) {
            subtitleView.setText(subtitle);
        } else {
            subtitleView.setText("番組内容なし");
        }

        TextView contentView = activity.findViewById(R.id.program_detail_content);
        String content = description.getContent();
        if (!"".equals(content)) {
            contentView.setText(content);
        } else {
            contentView.setText("番組詳細なし");
        }

        // 放送時間
        TextView timeView = activity.findViewById(R.id.program_detail_time);
        // 日付変換
        String startDateString = "";
        String endDateString = "";
        try {
            Date startDate = CommonUtils.string2Date(description.getStart_time());
            Date endDate   = CommonUtils.string2Date(description.getEnd_time());
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 ah:mm:ss", Locale.JAPAN);
            startDateString =  format.format(startDate).toString();
            endDateString   =  format.format(endDate).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        timeView.setText(startDateString + "～" + endDateString);

        // 出演者
        TextView actView = activity.findViewById(R.id.program_detail_act);
        actView.setText(description.getAct());

        // URL
        TextView urlView = activity.findViewById(R.id.program_detail_url);
        urlView.setText("http:" + description.getProgram_url());
        urlView.setAutoLinkMask(Linkify.WEB_URLS);


        loading.close();
    }
}
