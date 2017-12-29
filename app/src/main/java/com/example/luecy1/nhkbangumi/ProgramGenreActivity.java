package com.example.luecy1.nhkbangumi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
                        "http://api.nhk.or.jp/v2/pg/genre/130/g1/" + genreCode + "/"
                        ,adapter
                        ,ProgramGenreActivity.this.getApplicationContext());
                task.execute();

            }
        });
    }
}
