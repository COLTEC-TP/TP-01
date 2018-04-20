package br.ufmg.coltec.trabalhotp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TelaAdicionar.class);
                startActivity(intent);
            }
        });

//        BancoController crud = new BancoController(getBaseContext());
//        Cursor cursor = crud.carregaDados();
//
//        String[] nomeCampos = new String[] {CriaBanco.NOME_FILME, CriaBanco.DIRETOR, CriaBanco.ANO, CriaBanco.GENERO, CriaBanco.FAIXA};
//        int[] idViews = new int[] {R.id.lbl_filme_nome, R.id.lbl_filme_diretor, R.id.lbl_filme_ano, R.id.lbl_filme_genero, R.id.lbl_filme_faixa};
//
//        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.adapter_filmes,cursor,nomeCampos,idViews, 0);
//        ListView filmesListView = findViewById(R.id.listaFilmes);
//        filmesListView.setAdapter(adaptador);
    }
}
