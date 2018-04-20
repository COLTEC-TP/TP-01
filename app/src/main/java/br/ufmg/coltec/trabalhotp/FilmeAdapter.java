package br.ufmg.coltec.trabalhotp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by a2016951782 on 19/04/18.
 */

public class FilmeAdapter extends BaseAdapter {

    Filme filme1 = new Filme("dhoawidw", 389271, "jodiwajidw", "jdiwoajdw", "djoiwjdaw");
    Filme filme2 = new Filme("dhoawidw", 389271, "jodiwajidw", "jdiwoajdw", "djoiwjdaw");
    Filme filme3 = new Filme("dhoawidw", 389271, "jodiwajidw", "jdiwoajdw", "djoiwjdaw");
    Filme filme4 = new Filme("dhoawidw", 389271, "jodiwajidw", "jdiwoajdw", "djoiwjdaw");

    private ArrayList<Filme> filmes = new ArrayList<Filme>();
    private Context context;


    public FilmeAdapter(Context context) {
        this.filmes = new ArrayList<>();
        this.context = context;

        filmes.add(filme1);
        filmes.add(filme2);
        filmes.add(filme3);
        filmes.add(filme4);
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
        TextView lblGenero = newView.findViewById(R.id.lbl_filme_ano);
        TextView lblFaixa = newView.findViewById(R.id.lbl_filme_ano);

        // define o valor de cada um dos campos
        lblNome.setText(currentFilme.getNome());
        lblDiretor.setText(currentFilme.getDiretor());
        lblAno.setText(currentFilme.getAno().toString());
        lblGenero.setText(currentFilme.getGenero().toString());
        lblFaixa.setText(currentFilme.getFaixa().toString());

        return newView;

    }
}
