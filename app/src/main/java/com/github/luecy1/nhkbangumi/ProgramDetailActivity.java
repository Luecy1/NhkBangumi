package com.github.luecy1.nhkbangumi;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.luecy1.nhkbangumi.entity.description.DescriptionListRoot;
import com.github.luecy1.nhkbangumi.service.NhkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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


        Intent intent = getIntent();
        String service = intent.getStringExtra("service");
        String id = intent.getStringExtra("id");
        String area = intent.getStringExtra("area");

        // 通信
//        DetailAsyncTask task = new DetailAsyncTask(
//                buildUrl(area, service ,id)
//                ,this.getApplicationContext()
 //               ,this
        //               ,loading
//        );
//        task.execute();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.nhk.or.jp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NhkService nhkService = retrofit.create(NhkService.class);
        Call<List<DescriptionListRoot>> call = nhkService.programInfo(
                area,
                service,
                id,
                getApikey()
        );

        try {
            call.enqueue(
                    new Callback<List<DescriptionListRoot>>() {
                        @Override
                        public void onResponse(Call<List<DescriptionListRoot>> call, Response<List<DescriptionListRoot>> response) {
                            response.body().get(0).getList();
                        }

                        @Override
                        public void onFailure(Call<List<DescriptionListRoot>> call, Throwable t) {
                            Log.d("NhkBangumi", "NetworkError", t);
                        }
                    }
            );

        } catch (Exception e) {
            Log.d("NhkBangumi", "NetworkError",e);
        }


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

    private String getApikey() {

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

        return key;
    }
}
