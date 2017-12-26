package com.example.luecy1.nhkbangumi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
//何もしない
                return true;
            case R.id.menu_programList:
                Intent ListIntent = new Intent(this, ProgramListActivity.class);
                startActivity(ListIntent);
                return true;

            case R.id.menu_programGenre:
                Intent GenreIntent = new Intent(this, ProgramGenreActivity.class);
                startActivity(GenreIntent);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
