package com.reinaldoveazey.movieme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by a2016951561 on 05/04/18.
 */

public class MyAdapter extends BaseAdapter {
    private ArrayList<Movie> movies; // lista que conterá os filmes a serem exibidos
    private Context context;

    public MyAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();

        //... carrega dados da lista
        movies.add(new Movie(R.drawable.onde, "Lab Amarelo", "eu", "nada", 2000));

    }
    @Override
    public int getCount() {
        return this.movies.size();
    }
    @Override
    public Object getItem(int i) {
        return this.movies.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        Movie movieSelected = this.movies.get(i);

        // recupera a view do adapter que será customizada
        View newView = LayoutInflater.from(this.context).inflate(R.layout.list_item, viewGroup, false);

        // recupera cada um dos campos do item
        ImageView tarja = newView.findViewById(R.id.tarja);
        TextView nome = newView.findViewById(R.id.nome_fil);
        TextView dir = newView.findViewById(R.id.nome_dir);
        TextView genero = newView.findViewById(R.id.genero);
        TextView ano = newView.findViewById(R.id.ano);

        // define o valor de cada um dos campos
        tarja.setImageResource(movieSelected.getTarjaId());
        nome.setText(movieSelected.getName());
        dir.setText(movieSelected.getNameDir());
        genero.setText(movieSelected.getGenero());
        ano.setText(movieSelected.getAno().toString());
/*
        newView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Clicou no " + (i+1) + "°", Toast.LENGTH_SHORT).show();
            }
        });
*/
        return newView;
    }

}