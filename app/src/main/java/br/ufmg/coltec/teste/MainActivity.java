package br.ufmg.coltec.teste;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TelaAdicionar.class);
                startActivity(intent);
            }
        });

        FilmeAdapter adaptador = new FilmeAdapter(getBaseContext());
        if(adaptador.getCount()<0){
            Intent intent = new Intent(MainActivity.this, TelaAdicionar.class);
            startActivity(intent);
        } else{
            final BancoController crud = new BancoController(getBaseContext());
            Cursor cursor = crud.carregaDados();
            while(cursor.moveToNext()){
                Integer id = cursor.getInt(cursor.getColumnIndex("_id"));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                Integer ano = cursor.getInt(cursor.getColumnIndex("ano"));
                String diretor = cursor.getString(cursor.getColumnIndex("diretor"));
                String genero = cursor.getString(cursor.getColumnIndex("genero"));
                String faixa = cursor.getString(cursor.getColumnIndex("faixa"));
                Filme novoFilme = new Filme(nome, ano, diretor, genero, faixa);
                novoFilme.setId(id);
                adaptador.adicionarFilme(novoFilme);
            }
        }


        final ListView filmesListView = findViewById(R.id.listaFilmes);
        filmesListView.setAdapter(adaptador);

        filmesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, TelaFilme.class);
                Bundle args = new Bundle();
                Filme currentFilme = (Filme) filmesListView.getItemAtPosition(filmesListView.getPositionForView(view));

                args.putString("nome", currentFilme.getNome());
                args.putString("diretor", currentFilme.getDiretor());
                args.putInt("ano", currentFilme.getAno());
                args.putString("genero", currentFilme.getGenero());
                args.putString("faixa", currentFilme.getFaixa());
                args.putInt("_id", currentFilme.getId());
                intent.putExtras(args);
                startActivity(intent);
                return false;
            }
        });

    }
}
