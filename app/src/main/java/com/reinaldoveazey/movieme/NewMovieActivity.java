package com.reinaldoveazey.movieme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewMovieActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_movie);

        final EditText nome = findViewById(R.id.editar_nome);
        final EditText idade = findViewById(R.id.editar_idade);
        final EditText diretor = findViewById(R.id.editar_diretor);
        final EditText genero = findViewById(R.id.editar_genero);
        final EditText ano = findViewById(R.id.editar_ano);

        Button botaoOk = findViewById(R.id.editar_ok);

        botaoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pegar as entradas de texto
                String n = nome.getText().toString();
                String i = idade.getText().toString();
                String d = diretor.getText().toString();
                String g = genero.getText().toString();
                String a = ano.getText().toString();

                //Verificar se tudo foi preenchido antes de criar o filme
                if(n.length()>0&&i.length()>0&&d.length()>0&&g.length()>0&&a.length()>0) {
                    //Encher um bundle
                    Bundle bundle = new Bundle();
                    bundle.putString("nome", n);
                    bundle.putString("idade", i);
                    bundle.putString("diretor", d);
                    bundle.putString("genero", g);
                    bundle.putString("ano", a);

                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent); //Configurar para que responda a primeira atividade com o filme
                    finish(); //Finalizar essa atividade e assim voltar para a anterior
                }
                else {
                    Toast.makeText(NewMovieActivity.this,getString(R.string.Porfavorpreencha),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
