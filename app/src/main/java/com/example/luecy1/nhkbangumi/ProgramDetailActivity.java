package com.example.luecy1.nhkbangumi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.luecy1.nhkbangumi.httpTask.DetailAsyncTask;


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
        DetailAsyncTask task = new DetailAsyncTask(
                "http://api.nhk.or.jp/v2/pg/info/" + area +"/" + service + "/" + id + ".json"
                ,this.getApplicationContext()
                ,this
                ,loading
        );
        task.execute();

    }
}
