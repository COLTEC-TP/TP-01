package com.example.haasi.tp_01;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

public class MainActivity extends Activity {

    static DbHelper mDbHelper;
    static SQLiteDatabase db;
    static FilmeDAO dao;

    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new DbHelper(this);
        db = mDbHelper.getWritableDatabase();
        dao = new FilmeDAO(this);
    }

    @Override
    protected void onStart(){
        final ListView listView = this.showList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this,
                        FilmeActivity.class).putExtra("id", FilmeAdapter.filmes.get(position).getId());
                startActivity(intent);

            }
        });

        super.onStart();
    }

    @Override
    protected void onDestroy() {
        db.close();
        mDbHelper.close();
        super.onDestroy();
    }

     public ListView showList(){

        ListView moviesListView = findViewById(R.id.movies_list);
        moviesListView.setAdapter(new FilmeAdapter(MainActivity.this, dao));
        return moviesListView;
    }


}
