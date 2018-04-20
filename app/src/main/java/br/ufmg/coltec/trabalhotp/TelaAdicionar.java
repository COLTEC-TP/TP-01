package br.ufmg.coltec.trabalhotp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TelaAdicionar extends AppCompatActivity {

    private String[] generos = new String[]{"Ação", "Drama", "Comédia", "Suspense", "Ficção", "Romance"};

    private String[] faixa = new String[]{"Livre", "10 anos", "12 anos", "14 anos", "16 anos", "18 anos"};

    private Spinner spinner;

    private Spinner spinner1;

    private String faixaString;

    private String generoString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, generos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, faixa);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final EditText nome = findViewById(R.id.nomeDoFilme);
        final EditText diretor = findViewById(R.id.diretorDoFilme);
        final EditText ano = findViewById(R.id.anoDoFilme);
        Button add = findViewById(R.id.adicionar);
        spinner = findViewById(R.id.genero_spinner);
        spinner1 = findViewById(R.id.faixa_spinner);


        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                generoString = generos[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                faixaString = faixa[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BancoController crud = new BancoController(getBaseContext());
                String nomeString = nome.getText().toString();
                String diretorString = diretor.getText().toString();
                String anoString = ano.getText().toString();
                String resultado;

                resultado = crud.insereDado(nomeString, diretorString, anoString, generoString, faixaString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
