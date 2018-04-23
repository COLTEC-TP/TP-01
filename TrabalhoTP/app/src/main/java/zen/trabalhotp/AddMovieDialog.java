package zen.trabalhotp;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.time.Year;

public class AddMovieDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        final EditText name = new EditText(getContext());
        final EditText genre = new EditText(getContext());
        final EditText director = new EditText(getContext());
        final EditText ratingRange = new EditText(getContext());
        final EditText year = new EditText(getContext());
        year.setInputType(InputType.TYPE_CLASS_NUMBER);
        ratingRange.setInputType(InputType.TYPE_CLASS_NUMBER);

        name.setHint("Name");
        genre.setHint("Genre");
        director.setHint("Director");
        ratingRange.setHint("RatingRange");
        year.setHint("Year");

        linearLayout.addView(name);
        linearLayout.addView(genre);
        linearLayout.addView(director);
        linearLayout.addView(ratingRange);
        linearLayout.addView(year);

        return new AlertDialog.Builder(getActivity())
                .setMessage(R.string.adding)
                .setView(linearLayout)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(name.getText().toString().isEmpty() || genre.getText().toString().isEmpty() || director.getText().toString().isEmpty() || ratingRange.getText().toString().isEmpty() || year.getText().toString().isEmpty()){
                            Toast.makeText(getContext(), R.string.emptyfield, Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Movie movie = new Movie(name.getText().toString(), genre.getText().toString(), director.getText().toString(), Integer.parseInt(ratingRange.getText().toString()), Integer.parseInt(year.getText().toString()));
                            final DBcontroller crud = new DBcontroller(getContext());
                            crud.addData(movie.getName(), movie.getGenre(), movie.getDirector(), movie.getRatingRange(), movie.getYear());
                            Cursor cursor = crud.getID(movie);
                            movie.setId(cursor.getInt(cursor.getColumnIndex(NewDB.ID)));
                            MovieAdapter movieAdapter = (MovieAdapter) MainActivity.moviesListView.getAdapter();
                            movieAdapter.addMovie(movie);
                            movieAdapter.notifyDataSetChanged();
                            MainActivity.moviesListView.refreshDrawableState();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getContext(), R.string.canceled, Toast.LENGTH_SHORT).show();
                    }
                }).create();
    }
}