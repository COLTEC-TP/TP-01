package com.example.haasi.tp_01;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FilmeAdapter extends BaseAdapter {

    // lista que conterá a linguagens a serem exibidas
    private ArrayList<Filme> filmes;
    private Context context;

    public FilmeAdapter(Context context) {
        this.context = context;
        filmes= new ArrayList<>();


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
        lblLanguage.setText(filmes.getMovie() + " -- " + filmes.getType() + " -- " + filmes.getRate() + " -- " + filmes.getYear() + " -- " + filmes.getDirector());

        return lblLanguage;
    }
}
