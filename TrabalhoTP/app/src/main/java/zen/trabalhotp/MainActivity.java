package zen.trabalhotp;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Rect;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity{
    public FragmentManager fm = getSupportFragmentManager();
    MovieAdapter adapter;
    public static ListView moviesListView;
    private Cursor cursor;
    private View mDownView;
    private float mDownX;
    private float mDownXfinal = 0;
    private float mDownY;
    private float mDownYfinal = 0;
    private int mDownPosition;

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

        final DBcontroller crud = new DBcontroller(this.getBaseContext());
        cursor = crud.uploadData();
        adapter = new MovieAdapter(this.getBaseContext());
        if(cursor.getCount() > 0) {
            do {
                Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String genre = cursor.getString(cursor.getColumnIndex("genre"));
                String director = cursor.getString(cursor.getColumnIndex("director"));
                Integer ratingRange = cursor.getInt(cursor.getColumnIndex("ratingRange"));
                Integer year = cursor.getInt(cursor.getColumnIndex("year"));
                Movie newMovie = new Movie(name, genre, director, ratingRange, year);
                newMovie.setId(id);
                adapter.addMovie(newMovie);
            } while (cursor.moveToNext());
        }
        moviesListView.setAdapter(adapter);

        moviesListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN: {

                        Rect rect = new Rect();
                        int childCount = moviesListView.getChildCount();
                        int[] listViewCoords = new int[2];
                        moviesListView.getLocationOnScreen(listViewCoords);
                        int x = (int) event.getRawX() - listViewCoords[0];
                        int y = (int) event.getRawY() - listViewCoords[1];
                        View child;
                        for (int i = 0; i < childCount; i++) {
                            child = moviesListView.getChildAt(i);
                            child.getHitRect(rect);
                            if (rect.contains(x, y)) {
                                mDownView = child; // This is your down view
                                break;
                            }
                        }
                        mDownX = event.getRawX();
                        mDownY = event.getRawY();
                    }

                    case MotionEvent.ACTION_MOVE: {
                        if(mDownView == null){
                            return false;
                        }
                        mDownXfinal = event.getRawX();
                        mDownYfinal = event.getRawY();

                        if (Math.abs(mDownYfinal - mDownY) > Math.abs(mDownXfinal - mDownX)) {
                            return v.onTouchEvent(event);
                        }
                        else{
                            mDownView.setTranslationX(mDownXfinal - mDownX);
                            return true;
                        }
                    }

                    case MotionEvent.ACTION_UP: {
                        if(Math.abs(mDownXfinal - mDownX) > 500){
                            adapter = new MovieAdapter(getBaseContext());
                            Movie movie = (Movie) moviesListView.getItemAtPosition(mDownPosition);
                            crud.deleteData(movie.getId());
                            cursor = crud.uploadData();
                            if(cursor.getCount() > 0) {
                                do {
                                    Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                                    String name = cursor.getString(cursor.getColumnIndex("name"));
                                    String genre = cursor.getString(cursor.getColumnIndex("genre"));
                                    String director = cursor.getString(cursor.getColumnIndex("director"));
                                    Integer ratingRange = cursor.getInt(cursor.getColumnIndex("ratingRange"));
                                    Integer year = cursor.getInt(cursor.getColumnIndex("year"));
                                    Movie newMovie = new Movie(name, genre, director, ratingRange, year);
                                    newMovie.setId(id);
                                    adapter.addMovie(newMovie);
                                } while (cursor.moveToNext());
                            }
                            moviesListView.setAdapter(adapter);
                            Snackbar.make(moviesListView, "Movie deleted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }
                    }
                }
                return true;
            }
        });
    }
}