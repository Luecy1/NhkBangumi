package com.example.luecy1.nhkbangumi;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.luecy1.nhkbangumi.httpTask.ListAsyncTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class ProgramGenreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_genre);

        // LsitViewのセット
        ListView listView = findViewById(R.id.genre_list);

        final ProgramListAdapter adapter = new ProgramListAdapter(ProgramGenreActivity.this);
        listView.setAdapter(adapter);


        Button genreSearchButton = findViewById(R.id.genre_search_button);
        genreSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Loading loading = new Loading(ProgramGenreActivity.this);
                loading.show();

                Spinner genreSpinner = findViewById(R.id.genre_spinner);

                // ジャンルコードを設定
                String genreCode = "";
                if (genreSpinner.getSelectedItemPosition() < Const.GENRE_CODE.length) {
                    genreCode = Const.GENRE_CODE[genreSpinner.getSelectedItemPosition()];
                } else {
                    return;
                }

                Set<String> urlSet = buildUrl(genreCode);

                List<ListAsyncTask> taskList = new ArrayList<>();
                for (String url : urlSet) {
                    taskList.add(new ListAsyncTask(
                            url
                            , adapter
                            , loading)
                    );
                }

                for (ListAsyncTask task : taskList) {
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (adapter.getProgramList() == null) {
                    return;
                }
                String programId = adapter.getProgramList().get(position).getId();
                String service   = adapter.getProgramList().get(position).getService().getId();
                Intent detailIntent = new Intent(getApplicationContext(), ProgramDetailActivity.class);

                detailIntent.putExtra("id",programId);
                detailIntent.putExtra("service", service);
                startActivity(detailIntent);

            }
        });
    }

    /**
     * URLを生成します。
     * @return
     */
    private Set<String> buildUrl(String genre) {
        Set<String> urlSet = new HashSet<>();
        String baseUrl = "http://api.nhk.or.jp/v2/pg/genre";

        // 設定から地域を取得
        String area = PreferenceManager
                .getDefaultSharedPreferences(this)
                .getString("area", "130");

        // 設定からserviceを取得
        Set<String> serviceSet = PreferenceManager
                .getDefaultSharedPreferences(this)
                .getStringSet("service",new HashSet<String>(){{ add("tv"); }});

        // 現在日付の取得
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN);

        // 日本日付
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.HOUR, 9);
        nowDate = calendar.getTime();

        String jsonName = simpleDateFormat.format(nowDate) + ".json";

        //APIキーの取得
        String key;
        // 設定からAPIキーの取得
        key = PreferenceManager
                .getDefaultSharedPreferences(this)
                .getString("nhk_api_key", "");
        if ("".equals(key)) {
            try {
                ApplicationInfo info
                        = getApplicationContext().getPackageManager().getApplicationInfo(getApplicationContext().getPackageName(), PackageManager.GET_META_DATA);
                key = info.metaData.getString("nhkApiKey");
            } catch (PackageManager.NameNotFoundException e) {
                Toast.makeText(getApplicationContext(),"APIキーを取得できませんでした。", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

        for (String service : serviceSet) {
            urlSet.add(
                    String.format("%s/%s/%s/%s/%s?key=%s", baseUrl, area, service, genre, jsonName,key)
            );
        }

        return urlSet;
    }
}
