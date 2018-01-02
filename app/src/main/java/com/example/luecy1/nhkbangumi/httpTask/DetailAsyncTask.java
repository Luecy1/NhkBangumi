package com.example.luecy1.nhkbangumi.httpTask;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luecy1.nhkbangumi.Const;
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
import java.util.Calendar;
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

        Description tmpDescription = null;
        if (descriptionList.getList().getG1() != null) {
            tmpDescription = descriptionList.getList().getG1().get(0);
        } else if (descriptionList.getList().getG2() != null) {
            tmpDescription = descriptionList.getList().getG2().get(0);
        } else if (descriptionList.getList().getE1() != null) {
            tmpDescription = descriptionList.getList().getE1().get(0);
        } else if (descriptionList.getList().getE2() != null) {
            tmpDescription = descriptionList.getList().getE2().get(0);
        } else if (descriptionList.getList().getE3() != null) {
            tmpDescription = descriptionList.getList().getE3().get(0);
        } else if (descriptionList.getList().getE4() != null) {
            tmpDescription = descriptionList.getList().getE4().get(0);
        } else if (descriptionList.getList().getS1() != null) {
            tmpDescription = descriptionList.getList().getS1().get(0);
        } else if (descriptionList.getList().getS2() != null) {
            tmpDescription = descriptionList.getList().getS2().get(0);
        } else if (descriptionList.getList().getS3() != null) {
            tmpDescription = descriptionList.getList().getS3().get(0);
        } else if (descriptionList.getList().getS4() != null) {
            tmpDescription = descriptionList.getList().getS4().get(0);
        }
        if (tmpDescription == null) {
            return;
        }
        final Description description = tmpDescription;

        // 番組ロゴ画像
        ImageView logo = activity.findViewById(R.id.program_detail_logo);
        if (description.getProgram_logo() != null) {
            Picasso.with(context).load("http:" + description.getProgram_logo().getUrl()).into(logo);
        } else {
        }

        // 番組タイトル
        TextView titleView = activity.findViewById(R.id.program_detail_title);
        titleView.setText(description.getTitle());

        // 番組内容
        TextView subtitleView = activity.findViewById(R.id.program_detail_subtitle);
        String subtitle = description.getSubtitle();
        if (!"".equals(subtitle)) {
            subtitleView.setText(subtitle);
        } else {
            subtitleView.setText("番組内容なし");
        }

        // 番組詳細
        TextView contentView = activity.findViewById(R.id.program_detail_content);
        String content = description.getContent();
        if (!"".equals(content)) {
            contentView.setText(content);
        } else {
            contentView.setText("番組詳細なし");
        }

        // 放送地域
        TextView areaView = activity.findViewById(R.id.program_detail_area);
        String area = description.getArea().getName();
        if (!"".equals(area)) {
            areaView.setText(area);
        } else {
            areaView.setText("放送地域情報なし");
        }

        // 放送サービス
        TextView serviceView = activity.findViewById(R.id.program_detail_service);
        String service  = description.getService().getName();
        serviceView.setText(service);

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
        timeView.setText(startDateString + "～\n" + endDateString);

        // 出演者
        TextView actView = activity.findViewById(R.id.program_detail_act);
        actView.setText(description.getAct());

        // 番組ジャンル
        TextView genreView = activity.findViewById(R.id.program_detail_genre);
        StringBuilder genreStrBuilder = new StringBuilder();
        for (String genre : description.getGenres()) {
            if (genreStrBuilder.length() != 0) {
                genreStrBuilder.append(",");
            }
            genreStrBuilder.append(Const.GENRE_MAP_CODE.get(genre));
        }
        genreView.setText(genreStrBuilder);

        // 番組URL
        TextView urlView = activity.findViewById(R.id.program_detail_program_url);
        urlView.setText("http:" + description.getProgram_url());
        urlView.setAutoLinkMask(Linkify.WEB_URLS);

        // エピソードURL
        TextView epiUrlView = activity.findViewById(R.id.program_detail_episode_url);
        if (description.getEpisode_url() != null && !"".equals(description.getEpisode_url())) {
            epiUrlView.setText("http:" + description.getEpisode_url());
            epiUrlView.setAutoLinkMask(Linkify.WEB_URLS);
        } else {
            epiUrlView.setText("番組URL(放送回)なし");
        }

        // その他の情報
        TextView extraView = activity.findViewById(R.id.program_detail_extras);
        if (description.getExtras() != null) {
            extraView.setText(description.getExtras().getOndemand_program().getTitle());
            extraView.setText(description.getExtras().getOndemand_episode().getTitle());
        } else {
            extraView.setText("その他の情報なし");
        }

        // Googleカレンダーに登録を有効化
        Button calenderButton = activity.findViewById(R.id.program_detail_calender);
        calenderButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar startCal = Calendar.getInstance(Locale.JAPAN);
                        Calendar endCal   = Calendar.getInstance(Locale.JAPAN);
                        try {
                            Date startDate = CommonUtils.string2Date(description.getStart_time());
                            Date endDate   = CommonUtils.string2Date(description.getEnd_time());

                            startCal.setTime(startDate);
                            endCal.setTime(endDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(Intent.ACTION_EDIT);
                        intent.setType("vnd.android.cursor.item/event");
                        intent.putExtra("title", description.getTitle());
                        intent.putExtra("description", description.getSubtitle());
                        intent.putExtra("beginTime", startCal.getTimeInMillis()); //開始日時
                        intent.putExtra("endTime", endCal.getTimeInMillis()); //終了日時
                        activity.startActivity(intent);
                    }
                }
        );

        loading.close();
    }
}
