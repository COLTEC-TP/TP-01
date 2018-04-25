package com.ufmg.dener.movieme;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class Share extends AppCompatActivity {

    private Movie movie;
    ShareActionProvider shareProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        final int id = getIntent().getIntExtra("id", 0);
        DataBaseController crud = new DataBaseController(getBaseContext());
        System.out.println(id);
        this.movie = crud.loadData(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        MenuItem item = menu.findItem(R.id.action_share);
        shareProvider = (ShareActionProvider)MenuItemCompat.getActionProvider(item);

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        String dataShare = getBaseContext().getResources().getString(R.string.movie_name) + " " + movie.getName() + "\n"
                + getBaseContext().getResources().getString(R.string.movie_director) + " " + movie.getDirector() + "\n"
                + getBaseContext().getResources().getString(R.string.movie_genre) + " " + movie.getGenre() + "\n"
                + getBaseContext().getResources().getString(R.string.movie_rating) + " " + AddMovie.ages[movie.getAge()] + "\n"
                + getBaseContext().getResources().getString(R.string.movie_date) + " " + movie.getDate();

        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, dataShare);
        shareProvider.setShareIntent(sharingIntent);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_edit:
                Intent edit = new Intent(getBaseContext(), EditMovie.class);
                edit.putExtra("id", movie.getId());
                getBaseContext().startActivity(edit);
                finish();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
