package zen.trabalhotp;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Adapter;
import android.widget.Toast;

public class AddMovieDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage(R.string.adding)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DBcontroller crud = new DBcontroller(getContext());
                        String resultado;

                        resultado = crud.addData("filmeTeste", "Ação", "Tarantino", 18, 2006);
                        Toast.makeText(getContext(), resultado, Toast.LENGTH_LONG).show();

                        Cursor cursor = crud.uploadData();
                        MovieAdapter adapter = new MovieAdapter(getContext());
                        if(cursor != null) {
                            do {
                                String name = cursor.getString(cursor.getColumnIndex("name"));
                                String genre = cursor.getString(cursor.getColumnIndex("genre"));
                                String director = cursor.getString(cursor.getColumnIndex("director"));
                                Integer ratingRange = cursor.getInt(cursor.getColumnIndex("ratingRange"));
                                Integer year = cursor.getInt(cursor.getColumnIndex("year"));
                                adapter.addMovie(new Movie(name, genre, director, ratingRange, year));
                            } while (cursor.moveToNext());
                        }
                        MainActivity.moviesListView.setAdapter(adapter);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                    }
                }).create();
    }
}