package com.example.gui.tp;

/**
 * Created by Guilherme Assis on 22/04/2018.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class listAdapter extends ArrayAdapter<filmes> {

    private LayoutInflater mInflater;
    private ArrayList<filmes> filmes;
    private int mViewResourceId;

    public listAdapter(Context context, int textViewResourceId, ArrayList<filmes> filmes) {
        super(context, textViewResourceId, filmes);
        this.filmes = filmes;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        filmes filme = filmes.get(position);

        if (filme != null) {
            TextView Nome = (TextView) convertView.findViewById(R.id.textNome);
            TextView Genero = (TextView) convertView.findViewById(R.id.textGenero);
            TextView Diretor = (TextView) convertView.findViewById(R.id.textDiretor);
            TextView Ano = (TextView) convertView.findViewById(R.id.textAno);
            ImageView Faixa = convertView.findViewById(R.id.faixa);
            if (Nome != null) {
                Nome.setText(filme.getNOME());
            }
            if (Genero != null) {
                Genero.setText((filme.getGENERO()));
            }
            if (Diretor != null) {
                Diretor.setText((filme.getDIRETOR()));
            }
            if (Ano != null) {
                Ano.setText((filme.getANO()));
            }


            if (filme.getFAIXA().equals("livre")) {
                Faixa.setImageResource(R.drawable.livre);
            } else if (filme.getFAIXA().equals("dez")) {
                Faixa.setImageResource(R.drawable.dez);
            } else if (filme.getFAIXA().equals("doze")) {
                Faixa.setImageResource(R.drawable.doze);
            } else if (filme.getFAIXA().equals("quatorze")) {
                Faixa.setImageResource(R.drawable.quatorze);
            } else if (filme.getFAIXA().equals("dezesseis")) {
                Faixa.setImageResource(R.drawable.dezesseis);
            } else if(filme.getFAIXA().equals("dezoito")){
                Faixa.setImageResource(R.drawable.dezoito);
            }


        }

        return convertView;
    }
}
