package zen.trabalhotp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieShow extends AppCompatActivity {

    private ShareActionProvider mShareActionProvider;
    Bundle activityBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_show);

        activityBundle = this.getIntent().getExtras();

        TextView name = findViewById(R.id.name);
        TextView genre = findViewById(R.id.genre);
        TextView director = findViewById(R.id.director);
        ImageView ratingRange = findViewById(R.id.ratingRange);
        TextView year = findViewById(R.id.year);

        name.setText(activityBundle.getString("name"));
        genre.setText(activityBundle.getString("genre"));
        director.setText(activityBundle.getString("director"));
        year.setText(activityBundle.getString("year"));

        int ratingRangeInt = Integer.parseInt(activityBundle.getString("ratingRange"));

        if (ratingRangeInt < 10) {
            ratingRange.setImageResource(R.drawable.il);
        } else if (ratingRangeInt < 12) {
            ratingRange.setImageResource(R.drawable.i10);
        } else if (ratingRangeInt < 14) {
            ratingRange.setImageResource(R.drawable.i12);
        } else if (ratingRangeInt < 16) {
            ratingRange.setImageResource(R.drawable.i14);
        } else if (ratingRangeInt < 18) {
            ratingRange.setImageResource(R.drawable.i16);
        } else {
            ratingRange.setImageResource(R.drawable.i18);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        mShareActionProvider.setShareIntent(createShareIntent());
        return true;
    }

    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Name:" + activityBundle.getString("name")+ "\n" + "Genre:" +  activityBundle.getString("genre")+ "\n" + "Director:" + activityBundle.getString("director")+ "\n" + "Rating range:" + activityBundle.getString("ratingRange")+ "\n" + "Year:" + activityBundle.getString("year"));
        return shareIntent;
    }
}
