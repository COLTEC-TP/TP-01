package com.example.haasi.tp_01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class FilmeAdapter extends BaseAdapter {

    // lista que conterá os filmes a serem exibidos
    private ArrayList<Filme> filmes;
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

        // cria o componente que será carregado na lista
        TextView lblLanguage = new TextView(this.context);
        lblLanguage.setText(filmes.getId() + "--" + filmes.getMovie() + " -- " + filmes.getType() + " -- " + filmes.getRate() + " -- " + filmes.getYear() + " -- " + filmes.getDirector());

        return lblLanguage;
    }
}