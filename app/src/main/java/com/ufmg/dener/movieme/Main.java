package com.ufmg.dener.movieme;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.Date;

public class Main extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Movie>>{

    private ArrayList<Movie> movies = new ArrayList<>();
    private Adapter adapter;
    private static final int LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton addMovie = findViewById(R.id.addMovie);
        addMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newMovie = new Intent(Main.this, AddMovie.class);
                startActivity(newMovie);
            }
        });

        ListView listView = findViewById(R.id.listMovies);
        this.adapter = new Adapter(Main.this, movies);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent sharing = new Intent(Main.this, Share.class);

                Bundle args = new Bundle();
                args.putLong("id", id);

                sharing.putExtras(args);

                startActivity(sharing);
            }
        });

        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int i, Bundle bundle) {
        return new MovieLoader(getBaseContext());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Movie>> loader, ArrayList<Movie> movies) {
        this.adapter.clear();
        if (movies != null && !movies.isEmpty()) { adapter.addAll(movies); }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Movie>> loader) {
        this.adapter.clear();
    }
}
