package com.example.luecy1.nhkbangumi;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.AppLaunchChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luecy1.nhkbangumi.httpTask.ListAsyncTask;
import com.example.luecy1.nhkbangumi.httpTask.MainAsyncTask;
import com.example.luecy1.nhkbangumi.util.SettingUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初起動時、初期設定を行う
        if (AppLaunchChecker.hasStartedFromLauncher(this)) {
            // 2回目以降
        } else {
            SettingUtils.initSettings(this);
        }

        // Loading
        Loading loading = new Loading(this);
        loading.show();

        // LsitViewのセット
        ListView listView = findViewById(R.id.nowOnAirList);

        final MainActivityListAdapter adapter = new MainActivityListAdapter(MainActivity.this);

        listView.setAdapter(adapter);

        Set<String> urlSet = buildUrl();
        for (String url : urlSet) {
            Log.d("MyApp", url);
        }

        // 通信
        List<MainAsyncTask> taskList = new ArrayList<>();
        for (String url : urlSet) {
            taskList.add(new MainAsyncTask(
                    url
                    , adapter
                    , loading));
        }
        // 実行
        for (MainAsyncTask task : taskList) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

        // 時刻の表示
        final TextView clockText = findViewById(R.id.clock_text);
        final DateFormat clockFormat = new SimpleDateFormat("hh:mm");
        final Handler clockHandler = new Handler();
        final Runnable run = new Runnable() {
            @Override
            public void run() {
                // 時刻の更新
                final Date nowDate = new Date();
                String clockStr = clockFormat.format(nowDate);
                clockText.setText(clockStr);

                clockHandler.postDelayed(this, 1000);
            }
        };
        clockHandler.post(run);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case R.id.menu_programList:
                Intent ListIntent = new Intent(this, ProgramListActivity.class);
                startActivity(ListIntent);
                return true;

            case R.id.menu_programGenre:
                Intent GenreIntent = new Intent(this, ProgramGenreActivity.class);
                startActivity(GenreIntent);
                return true;

            case R.id.menu_setting:
                Intent settingIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * URLを生成します。
     * @return
     */
    private Set<String> buildUrl() {
        Set<String> urlSet = new HashSet<>();
        String baseUrl = "http://api.nhk.or.jp/v2/pg/now";

        // 設定から地域を取得
        String area = PreferenceManager
                .getDefaultSharedPreferences(this)
                .getString("area", "130");

        // 設定からserviceを取得
        Set<String> serviceSet = PreferenceManager
                .getDefaultSharedPreferences(this)
                .getStringSet("service",new HashSet<String>());

        //APIキーの取得
        String key = "";
        // 設定からAPIキーの取得
        key = PreferenceManager
                .getDefaultSharedPreferences(this)
                .getString("nhk_api_key", "");

        // 存在しない場合App標準APIキーを使用
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
                    String.format("%s/%s/%s.json?key=%s", baseUrl, area, service, key)
            );
        }

        return urlSet;
    }

}
