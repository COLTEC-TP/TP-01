package com.example.gui.tp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class cadastrarFilmes extends AppCompatActivity {

    private Spinner spinnerFaixa;
    private String[] faixa = new String[]{"livre", "dez", "doze", "quatorze", "dezesseis", "dezoito"};
    private String FaixaEtaria;
    EditText etNome, etGenero, etDiretor, etAno;
    Button btnAdd;
    bancoDeDados myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // tela cheia
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Retirar action Bar
        getSupportActionBar().hide();

        setContentView(R.layout.activity_cadastrarfilmes);



        spinnerFaixa = findViewById(R.id.etFaixa);
        etDiretor = (EditText) findViewById(R.id.etDiretor);
        etAno = (EditText) findViewById(R.id.etAno);
        etNome = (EditText) findViewById(R.id.etNome);
        etGenero = (EditText) findViewById(R.id.etGenero);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        myDB = new bancoDeDados(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, faixa);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFaixa.setAdapter(adapter);

        spinnerFaixa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                FaixaEtaria = faixa[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fNome = etNome.getText().toString();
                String fGenero = etGenero.getText().toString();
                String fDiretor = etDiretor.getText().toString();
                String fAno = etAno.getText().toString();
                if(fNome.length() != 0 && fGenero.length() != 0 && fDiretor.length() != 0 && fAno.length() != 0){
                    AddData(fNome,fGenero, fDiretor, fAno, FaixaEtaria);
                    etDiretor.setText("");
                    etAno.setText("");
                    etGenero.setText("");
                    etNome.setText("");

                    //Chama a acitivity de listar filmes
                    Intent intent = new Intent(cadastrarFilmes.this,listarFilmes.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(cadastrarFilmes.this,"Voce nao digitou todas as informacoes",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public void AddData(String Nome,String Genero, String Diretor, String Ano, String Faixa){
        boolean insertData = myDB.addData(Nome,Genero,Diretor, Ano, Faixa);

        if(insertData==true){
            Toast.makeText(cadastrarFilmes.this,"Inserido com sucesso",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(cadastrarFilmes.this,"Algo esta errado...",Toast.LENGTH_LONG).show();
        }
    }
}
