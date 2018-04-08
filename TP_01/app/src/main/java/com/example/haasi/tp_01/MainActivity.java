package com.example.haasi.tp_01;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import android.content.Context;
import android.widget.Toast;

public class MainActivity extends Activity {

    static DbHelper mDbHelper;
    static SQLiteDatabase db;

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

        this.showList();

        Toast.makeText(this, "oi", Toast.LENGTH_LONG);

        this.removeElement(23);

    }

    public void showList(){
        FilmeDAO dao = new FilmeDAO(this);

        ListView moviesListView = findViewById(R.id.movies_list);
        moviesListView.setAdapter(new FilmeAdapter(MainActivity.this, dao));
    }

    public void removeElement(Integer id){
        FilmeDAO dao = new FilmeDAO(this);
        dao.deletar(id);
        this.showList();
    }


    @Override
    protected void onDestroy() {
        db.close();
        mDbHelper.close();
        super.onDestroy();
    }
}
