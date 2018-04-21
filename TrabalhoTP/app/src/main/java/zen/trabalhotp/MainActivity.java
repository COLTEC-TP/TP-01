package zen.trabalhotp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
    View.OnClickListener undoListener;
    int undoVerification = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FloatingActionButton fab = findViewById(R.id.floatingActionButton);
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
                        if (mDownView == null) {
                            return false;
                        }
                        mDownXfinal = event.getRawX();
                        mDownYfinal = event.getRawY();
                        Log.d("test", ((Float) mDownXfinal).toString());

                        if (Math.abs(mDownYfinal - mDownY) > Math.abs(mDownXfinal - mDownX)) {
                            return v.onTouchEvent(event);
                        } else {
                            mDownView.setTranslationX(mDownXfinal - mDownX);
                            return true;
                        }
                    }

                    case MotionEvent.ACTION_UP: {
                        if (mDownView == null) {
                            return false;
                        }
                        mDownXfinal = event.getRawX();
                        mDownYfinal = event.getRawY();
                        Movie movie = (Movie) moviesListView.getItemAtPosition(moviesListView.getPositionForView(mDownView));
                        if (movie == null) {
                            return false;
                        }
                        if (mDownXfinal - mDownX < -600 && !moviesListView.getAdapter().isEmpty()) {
                            final int position = moviesListView.getPositionForView(mDownView);
                            final Movie tempMovie = new Movie(movie.getName(), movie.getGenre(), movie.getDirector(), movie.getRatingRange(), movie.getYear());
                            final int id = movie.getId();
                            tempMovie.setId(id);
                            MovieAdapter adapter = (MovieAdapter) MainActivity.moviesListView.getAdapter();
                            adapter.deleteMovie(movie);
                            adapter.notifyDataSetChanged();
                            MainActivity.moviesListView.refreshDrawableState();

                            undoListener = new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    MovieAdapter adapter = (MovieAdapter) MainActivity.moviesListView.getAdapter();
                                    adapter.addMovieOn(position, tempMovie);
                                    adapter.notifyDataSetChanged();
                                    MainActivity.moviesListView.refreshDrawableState();
                                    undoVerification = 0;
                                }
                            };
                            Snackbar.make(moviesListView, "Movie deleted", Snackbar.LENGTH_LONG).setAction(R.string.undo, undoListener).show();
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (!((MovieAdapter) MainActivity.moviesListView.getAdapter()).contains(tempMovie)) {
                                        crud.deleteData(id);
                                    }
                                    undoVerification = 1;
                                }
                            }, 3000);
                            return true;
                        }
                        else if (mDownXfinal - mDownX > 600 && !moviesListView.getAdapter().isEmpty()) {
                            Intent intent = new Intent(MainActivity.this, MovieShow.class);
                            Bundle args = new Bundle();
                            args.putString("name", movie.getName());
                            args.putString("genre", movie.getGenre());
                            args.putString("director", movie.getDirector());
                            args.putString("ratingRange", movie.getRatingRange().toString());
                            args.putString("year", movie.getYear().toString());
                            intent.putExtras(args);
                            startActivity(intent);
                            mDownView.animate().translationX(0).setDuration(100);
                        }
                        else {
                            if (mDownView == null) {
                                return false;
                            }
                            mDownXfinal = event.getRawX();
                            mDownYfinal = event.getRawY();

                            if (Math.abs(mDownYfinal - mDownY) > Math.abs(mDownXfinal - mDownX)) {
                                if (mDownYfinal - mDownY < 0) {
                                    fab.animate().translationY(220).setDuration(100);
                                } else {
                                    fab.animate().translationY(0).setDuration(200);
                                }
                                return v.onTouchEvent(event);
                            } else {
                                mDownView.setTranslationX(mDownXfinal - mDownX);
                                mDownView.animate().translationX(0).setDuration(100);
                                return true;
                            }
                        }
                    }
                }
                return true;
            }
        });
    }
}