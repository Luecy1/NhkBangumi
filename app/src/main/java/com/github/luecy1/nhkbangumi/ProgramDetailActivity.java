package com.github.luecy1.nhkbangumi;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.luecy1.nhkbangumi.databinding.ActivityProgramDetailBinding;
import com.github.luecy1.nhkbangumi.task.DetailAsyncTask;


public class ProgramDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_detail);

        // Title
        setTitle("番組詳細");

        // Loading
        Loading loading = new Loading(this);
        loading.show();

        ActivityProgramDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_program_detail);

        Intent intent = getIntent();
        String service = intent.getStringExtra("service");
        String id = intent.getStringExtra("id");
        String area = intent.getStringExtra("area");

        // 通信
        DetailAsyncTask task = new DetailAsyncTask(
                buildUrl(area, service ,id)
                ,this.getApplicationContext()
                ,this
                ,loading
        );
        task.execute();

    }

    private String buildUrl(String area, String service, String id) {

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

        return String.format("http://api.nhk.or.jp/v2/pg/info/%s/%s/%s.json?key=%s", area, service, id, key);
    }
}
