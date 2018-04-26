package com.reinaldoveazey.movieme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

        //Arquivo.carregarArquivo(context); //Carregar a partir do arquivo

        //... carrega dados da lista
        movies.add(new Movie(0, "Vamos começar", "Nesta tela você poderá compartilhar seu filme", "Remova este item quando quiser", 0));
    }
    @Override
    public int getCount() {
        return this.movies.size();
    }
    @Override
    public Object getItem(int i) {
        return this.movies.get(i);
    }
    public Movie getItemM(int i){
        return this.movies.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    public ArrayList<Movie> getMovies(){
        return movies;
    }

    static public Integer obterImagemTarja(int idade){
        switch (idade){
            case 0:
                return R.drawable.livre;
            case 10:
                return R.drawable.t10;
            case 12:
                return R.drawable.t12;
            case 14:
                return R.drawable.t14;
            case 16:
                return R.drawable.t16;
            case 18:
                return R.drawable.t18;
            default:
                return R.drawable.naoconhecido;
        }
    }

    public Movie remover (int pos){
        return movies.remove(pos);
    }
    public void adicionar (Movie m){
        movies.add(m);
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

        return newView;
    }
}