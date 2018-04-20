package com.ufmg.dener.movieme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Dener on 14/04/2018.
 */

public class Adapter extends ArrayAdapter<Movie> {

    public Adapter(Context context, ArrayList<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final Movie movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_design, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        name.setText(movie.getName());

        TextView genre = convertView.findViewById(R.id.genre);
        genre.setText(movie.getGenre());

        TextView director = convertView.findViewById(R.id.director);
        director.setText(movie.getDirector());

        TextView date = convertView.findViewById(R.id.date);
        date.setText(String.valueOf( movie.getDate() ));

        ImageView age = convertView.findViewById(R.id.age);
        age.setImageDrawable(getContext().getDrawable(movie.getAge()));

        ImageButton edit = convertView.findViewById(R.id.edit);
        edit.setFocusable(false);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(getContext(), EditMovie.class);
                edit.putExtra("id", movie.getId());
                getContext().startActivity(edit);
            }
        });

        ImageButton delete = convertView.findViewById(R.id.delete);
        delete.setFocusable(false);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage( getContext().getResources().getString(R.string.movie_delete_message) )
                        .setTitle( getContext().getResources().getString(R.string.movie_date) )
                        .setPositiveButton( getContext().getResources().getString(R.string.choose_yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                DataBaseController crud = new DataBaseController(getContext());
                                crud.delete(movie.getId());
                                remove(movie);
                                notifyDataSetChanged();
                                Toast.makeText(getContext(), getContext().getString(R.string.done), Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(getContext().getResources().getString(R.string.choose_no), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getContext(), getContext().getResources().getString(R.string.canceled), Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        ImageButton share = convertView.findViewById(R.id.share);
        share.setFocusable(false);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String age = null;
                if(movie.getAge() == R.drawable.ic_free)
                    age = "L";
                if(movie.getAge() == R.drawable.ic_ten)
                    age = "10";
                if(movie.getAge() == R.drawable.ic_twelve)
                    age = "12";
                if(movie.getAge() == R.drawable.ic_fourteen)
                    age = "14";
                if(movie.getAge() == R.drawable.ic_sixteen)
                    age = "16";
                if(movie.getAge() == R.drawable.ic_eighteen)
                    age = "18";

                String dataShare = getContext().getResources().getString(R.string.movie_name) + " " + movie.getName() + "\n"
                        + getContext().getResources().getString(R.string.movie_director) + " " + movie.getDirector() + "\n"
                        + getContext().getResources().getString(R.string.movie_rating) + " " + age + "\n"
                        + getContext().getResources().getString(R.string.movie_genre) + " " + movie.getGenre() + "\n"
                        + getContext().getResources().getString(R.string.movie_date) + " " + movie.getDate();

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, dataShare);
                sendIntent.setType("text/plain");
                getContext().startActivity(sendIntent);
            }
        });

        return convertView;
    }

}
