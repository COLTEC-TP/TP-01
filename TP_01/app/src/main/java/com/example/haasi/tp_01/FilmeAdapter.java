package com.example.haasi.tp_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class FilmeAdapter extends BaseAdapter {

    // lista que conterá os filmes a serem exibidos
    static ArrayList<Filme> filmes;
    private Context context;
    private FilmeDAO dao;



    public FilmeAdapter(Context context, FilmeDAO Fdao) {
        this.dao = Fdao;
        this.context = context;
        //Pegamos os filmes por meio do cursor na classe FilmesDAO
        filmes = dao.retornarTodos();

    }
    @Override
    public int getCount() {
        return this.filmes.size();
    }
    @Override
    public Object getItem(int i) {
        return this.filmes.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Filme filmes = this.filmes.get(i);

        View newView = LayoutInflater.from(this.context).inflate(R.layout.movies_adapter, viewGroup, false);

        // cria o componente que será carregado na lista

        TextView lblFilme = newView.findViewById(R.id.lbl_movie);
        TextView lblDiretor = newView.findViewById(R.id.lbl_movie_director);
        TextView lblAno = newView.findViewById(R.id.lbl_movie_year);
        TextView lblGenero = newView.findViewById(R.id.lbl_movie_gender);
        ImageView lblFoto = newView.findViewById(R.id.lbl_movie_rating);


        lblFilme.setText(filmes.getMovie());
        lblDiretor.setText(filmes.getDirector());
        lblAno.setText(filmes.getYear());

        if (filmes.getType() == 0) {
            lblGenero.setText(context.getResources().getText(R.string.action));
        } else if (filmes.getType() == 1) {
            lblGenero.setText(context.getResources().getText(R.string.drama));
        } else if (filmes.getType() == 2) {
            lblGenero.setText(context.getResources().getText(R.string.comedy));
        } else if (filmes.getType() == 3) {
            lblGenero.setText(context.getResources().getText(R.string.thriller));
        } else if (filmes.getType() == 4) {
            lblGenero.setText(context.getResources().getText(R.string.fiction));
        } else if (filmes.getType() == 5) {
            lblGenero.setText(context.getResources().getText(R.string.romance));
        }

        if ((filmes.getRate() % 6) == 1) {
            lblFoto.setBackgroundResource(R.drawable.livre);
        } else if ((filmes.getRate() % 6) == 2) {
            lblFoto.setBackgroundResource(R.drawable.mais10);
        } else if ((filmes.getRate() % 6) == 3) {
            lblFoto.setBackgroundResource(R.drawable.mais12);
        } else if ((filmes.getRate() % 6) == 4) {
            lblFoto.setBackgroundResource(R.drawable.mais14);
        } else if ((filmes.getRate() % 6) == 5) {
            lblFoto.setBackgroundResource(R.drawable.mais16);
        } else if ((filmes.getRate() % 6) == 0) {
            lblFoto.setBackgroundResource(R.drawable.mais18);
        }

        return newView;
    }
}