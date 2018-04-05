package com.example.haasi.tp_01;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class FormActivity extends Activity {

    String[] selecao = {"Ação", "Drama", "Comédia", "Suspense", "Ficção", "Romance"};
    Button botao;
    EditText filme;
    EditText diretor;
    EditText ano;
    Spinner spin;
    RadioGroup indicacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Linkando o Java com o xml
        spin = findViewById(R.id.spinner2);
        filme = findViewById(R.id.filme);
        diretor = findViewById(R.id.diretor);
        ano = findViewById(R.id.ano);
        indicacao = findViewById(R.id.indicacao);
        botao = findViewById(R.id.botao1);

        //Fazendo o Spinner funcionar
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, selecao);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adaptador);

        //Fazendo o "Submit" Funcionar
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filme1 = filme.getText().toString();
                String diretor1 = diretor.getText().toString();
                String ano1 = ano.getText().toString();
                String spin1 = String.valueOf(spin.getSelectedItemPosition());
                String indicacao1 = String.valueOf(indicacao.getCheckedRadioButtonId());
                Context contexto = getApplicationContext();
                Toast toast = Toast.makeText(contexto, filme1, Toast.LENGTH_SHORT);
                toast.show();
                toast = Toast.makeText(contexto, diretor1, Toast.LENGTH_SHORT);
                toast.show();
                toast = Toast.makeText(contexto, ano1, Toast.LENGTH_SHORT);
                toast.show();
                toast = Toast.makeText(contexto, spin1, Toast.LENGTH_SHORT);
                toast.show();
                toast = Toast.makeText(contexto, indicacao1, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

}