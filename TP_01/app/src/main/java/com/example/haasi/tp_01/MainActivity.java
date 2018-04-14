package com.example.haasi.tp_01;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import android.content.Context;
import android.widget.Toast;

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
        final ListView moviesListView = this.showList();

        //Caso user clique em um dos filmes
        moviesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                View v = getViewByPosition(position, moviesListView);

                Intent intent = new Intent(MainActivity.this, FilmeActivity.class).putExtra("id", v.getId());
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

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    public void removeElement(Integer id){
        FilmeDAO dao = new FilmeDAO(this);
        dao.deletar(id);
        this.showList();
    }



}
