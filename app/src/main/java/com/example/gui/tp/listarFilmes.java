package com.example.gui.tp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Guilherme Assis on 2016-05-13.
 */
public class listarFilmes extends AppCompatActivity {

    bancoDeDados myDB;
    ArrayList<filmes> filmeList;
    ListView listView;
    filmes filme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // tela cheia
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Retirar action Bar
        getSupportActionBar().hide();

        setContentView(R.layout.viewcontents_layout);

        //Botao adicionar filme
        Button adicionarFilme = findViewById(R.id.adicionarFilme);
        adicionarFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(listarFilmes.this, cadastrarFilmes.class);
                startActivity(intent);
            }
        });

        //Botao excluir e compartilhar
        Button excluirEcompartilharBotao = findViewById(R.id.excluirEcompartilharBotao);
        excluirEcompartilharBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(listarFilmes.this, excluirEcompartilhar.class);
                startActivity(intent);
            }
        });



        myDB = new bancoDeDados(this);

        filmeList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(listarFilmes.this,"Lista vazia!",Toast.LENGTH_LONG).show();
        }else{
            int i=0;
            while(data.moveToNext()){
                filme = new filmes(data.getString(1),data.getString(2),data.getString(3),data.getString(4),data.getString(5));
                filmeList.add(i,filme);
                System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getString(3)+" "+data.getString(4)+" "+data.getString(5));
                System.out.println(filmeList.get(i).getNOME());
                i++;
            }
            listAdapter adapter =  new listAdapter(this,R.layout.list_adapter_view, filmeList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

        }


    }
}