package com.example.luecy1.nhkbangumi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.luecy1.nhkbangumi.httpTask.ListAsyncTask;

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

                // 通信
                ListAsyncTask task = new ListAsyncTask(
                        "http://api.nhk.or.jp/v2/pg/genre/130/tv/" + genreCode + "/"
                        ,adapter
                        ,ProgramGenreActivity.this.getApplicationContext()
                        ,loading);
                task.execute();

            }
        });
/*
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
        });*/
    }
}
