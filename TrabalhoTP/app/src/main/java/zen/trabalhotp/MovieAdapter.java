package zen.trabalhotp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private ArrayList<Movie> movies;
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList();
    }



    public void addMovie(Movie movie){
        this.movies.add(movie);
    }

    public void addMovieOn(int position, Movie movie){
        if(position == 0){
            this.movies.add(movie);
        }
        else{
            this.movies.add(position, movie);
        }
    }

    public boolean contains(Movie movie) {
        return this.movies.contains(movie);
    }

    public void deleteMovie(Movie movie){
        this.movies.remove(movie);
    }

    @Override
    public boolean isEmpty(){return movies.isEmpty();}

    @Override
    public int getCount() {
        return this.movies.size();
    }

    @Override
    public Object getItem(int position) {
        return this.movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Movie movie = this.movies.get(i);

        View newView = LayoutInflater.from(this.context).inflate(R.layout.movies, viewGroup, false);

        TextView lblName = newView.findViewById(R.id.name);
        TextView lblGenre = newView.findViewById(R.id.genre);
        TextView lblDirector = newView.findViewById(R.id.director);
        ImageView lblRatingRange = newView.findViewById(R.id.ratingRange);
        TextView lblYear = newView.findViewById(R.id.year);

        lblName.setText(movie.getName());
        lblGenre.setText(movie.getGenre());
        lblDirector.setText(movie.getDirector());
        lblYear.setText(movie.getYear().toString());

        if (movie.getRatingRange() < 10) {
            lblRatingRange.setImageResource(R.drawable.il);
        } else if (movie.getRatingRange() < 12) {
            lblRatingRange.setImageResource(R.drawable.i10);
        } else if (movie.getRatingRange() < 14) {
            lblRatingRange.setImageResource(R.drawable.i12);
        } else if (movie.getRatingRange() < 16) {
            lblRatingRange.setImageResource(R.drawable.i14);
        } else if (movie.getRatingRange() < 18) {
            lblRatingRange.setImageResource(R.drawable.i16);
        } else {
            lblRatingRange.setImageResource(R.drawable.i18);
        }

        return newView;
    }
}
