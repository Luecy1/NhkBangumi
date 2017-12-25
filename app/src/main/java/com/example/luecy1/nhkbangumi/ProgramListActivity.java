package com.example.luecy1.nhkbangumi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luecy1.nhkbangumi.httpTask.MyAsyncTask;

public class ProgramListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_list);

        // LsitViewのセット
        ListView listView = findViewById(R.id.programListListView);

        final ProgramListAdapter adapter = new ProgramListAdapter(ProgramListActivity.this);

        listView.setAdapter(adapter);

        // 通信
        MyAsyncTask task = new MyAsyncTask(
                "http://api.nhk.or.jp/v2/pg/list/130/g1/"
                ,adapter
                ,this.getApplicationContext());
        task.execute();

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if (adapter.getProgramList() == null) {
                            return;
                        }
                        String programId = adapter.getProgramList().getList().getG1().get(position).getId();
                        Intent detailIntent = new Intent(getApplicationContext(), ProgramDetailActivity.class);

                        detailIntent.putExtra("id",programId);
                        startActivity(detailIntent);

                    }
                }
        );
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

            case R.id.menu_home:
                finish();
                return true;
            case R.id.menu_programList:
// 何もしない
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}