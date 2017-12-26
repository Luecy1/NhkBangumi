package com.example.luecy1.nhkbangumi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ProgramGenreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_genre);


        Button genreSearchButton = findViewById(R.id.genre_search_button);
        genreSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner genreSpinner = findViewById(R.id.genre_spinner);

                Toast.makeText(
                        ProgramGenreActivity.this,
                        "" + String.valueOf(genreSpinner.getSelectedItemPosition()),
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}
