package br.ufmg.coltec.teste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by a2016951782 on 19/04/18.
 */

public class FilmeAdapter extends BaseAdapter {


    private ArrayList<Filme> filmes = new ArrayList<Filme>();
    private Context context;


    public FilmeAdapter(Context context) {
        this.filmes = new ArrayList<>();
        this.context = context;

    }

    public void adicionarFilme(Filme filme){
        this.filmes.add(filme);
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
    public View getView(final int index, View view, ViewGroup viewGroup) {

        Filme currentFilme = this.filmes.get(index);

        // recupera a view do adapter que será customizada
        View newView = LayoutInflater.from(this.context).inflate(R.layout.adapter_filmes, viewGroup, false);

        // recupera cada um dos campos do item
        TextView lblNome = newView.findViewById(R.id.lbl_filme_nome);
        TextView lblDiretor = newView.findViewById(R.id.lbl_filme_diretor);
        TextView lblAno = newView.findViewById(R.id.lbl_filme_ano);
        TextView lblGenero = newView.findViewById(R.id.lbl_filme_genero);
        ImageView lblFaixa = newView.findViewById(R.id.lbl_filme_faixa);

        // define o valor de cada um dos campos
        lblNome.setText(currentFilme.getNome());
        lblDiretor.setText(currentFilme.getDiretor());
        lblAno.setText(currentFilme.getAno().toString());
        lblGenero.setText(currentFilme.getGenero());

        if (currentFilme.getFaixa().equals("Livre")) {
            lblFaixa.setImageResource(R.drawable.livre_icon);
        } else if (currentFilme.getFaixa().equals("10 anos")) {
            lblFaixa.setImageResource(R.drawable.idade10);
        } else if (currentFilme.getFaixa().equals("12 anos")) {
            lblFaixa.setImageResource(R.drawable.idade12);
        } else if (currentFilme.getFaixa().equals("14 anos")) {
            lblFaixa.setImageResource(R.drawable.idade14);
        } else if (currentFilme.getFaixa().equals("16 anos")) {
            lblFaixa.setImageResource(R.drawable.idade16);
        } else if(currentFilme.getFaixa().equals("18 anos")){
            lblFaixa.setImageResource(R.drawable.idade18);
        }


        return newView;

    }
}
