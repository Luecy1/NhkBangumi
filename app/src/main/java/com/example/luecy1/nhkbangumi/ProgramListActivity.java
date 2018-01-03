package com.example.luecy1.nhkbangumi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luecy1.nhkbangumi.httpTask.ListAsyncTask;
import com.example.luecy1.nhkbangumi.util.CommonUtils;

public class ProgramListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_list);

        if (!CommonUtils.netWorkCheck(this.getApplicationContext())) {
            Toast.makeText(this, "ネットワークに接続されていません。",Toast.LENGTH_SHORT).show();
            return;
        }

        // Loading
        Loading loading = new Loading(this);
        loading.show();

        // LsitViewのセット
        ListView listView = findViewById(R.id.programListListView);

        final ProgramListAdapter adapter = new ProgramListAdapter(ProgramListActivity.this);

        listView.setAdapter(adapter);

        // 通信
        ListAsyncTask task = new ListAsyncTask(
                "http://api.nhk.or.jp/v2/pg/list/"
                ,adapter
                ,this.getApplicationContext()
                ,loading);
        task.execute();

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
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
                }
        );
    }

}
