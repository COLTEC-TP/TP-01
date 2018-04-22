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
        movies.add(new Movie(18, "Disneyland with Honda", "Sr. Incrível", "Migué supremo", 2013));
        movies.add(new Movie(0, "Entrei num armário em Hogwarts e saí em Nárnia", "eu", "n", 2018));
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

    private Integer obterImagemTarja(int idade){
        switch (idade){
            case 0:
                return R.drawable.ic_launcher_background;
            case 10:
                return R.drawable.ic_launcher_background;
            case 12:
                return R.drawable.ic_launcher_background;
            case 14:
                return R.drawable.ic_launcher_background;
            case 16:
                return R.drawable.ic_launcher_background;
            case 18:
                return R.drawable.ic_launcher_background;
            default:
                return null;
        }
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
        tarja.setImageResource(obterImagemTarja(movieSelected.getTarja()));
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