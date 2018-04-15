package zen.trabalhotp;

import android.content.Context;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public FragmentManager fm = getSupportFragmentManager();
    public static ListView moviesListView;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddMovieDialog dialog = new AddMovieDialog();
                dialog.show(fm, "addMovie");
            }
        });

        moviesListView = findViewById(R.id.movies_list);

//        final DBcontroller crud = new DBcontroller(this.getBaseContext());
//        cursor = crud.uploadData();
//        MovieAdapter adapter = new MovieAdapter(this.getBaseContext());
//        if(cursor != null) {
//            do {
//                Integer id = cursor.getInt(cursor.getColumnIndex("id"));
//                String name = cursor.getString(cursor.getColumnIndex("name"));
//                String genre = cursor.getString(cursor.getColumnIndex("genre"));
//                String director = cursor.getString(cursor.getColumnIndex("director"));
//                Integer ratingRange = cursor.getInt(cursor.getColumnIndex("ratingRange"));
//                Integer year = cursor.getInt(cursor.getColumnIndex("year"));
//                Movie newMovie = new Movie(name, genre, director, ratingRange, year);
//                newMovie.setId(id);
//                adapter.addMovie(newMovie);
//            } while (cursor.moveToNext());
//        }
//        moviesListView.setAdapter(adapter);
//
//        moviesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Integer code;
//                Movie movie = (Movie) moviesListView.getItemAtPosition(position);
//                crud.deleteData(movie.getId());
//                Snackbar.make(view, "Movie deleted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//                return false;
//            }
//        });
    }
}