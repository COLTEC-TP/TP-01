package br.ufmg.coltec.teste;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TelaFilme extends AppCompatActivity {

    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_filme);

        final BancoController crud = new BancoController(getBaseContext());

        Bundle activityBundle = this.getIntent().getExtras();
        final String nome = activityBundle.getString("nome");
        final String diretor = activityBundle.getString("diretor");
        final Integer ano = activityBundle.getInt("ano");
        final String genero = activityBundle.getString("genero");
        final String faixa = activityBundle.getString("faixa");
        final Integer id = activityBundle.getInt("_id");

        TextView nome1 = findViewById(R.id.textView);
        TextView diretor1 = findViewById(R.id.textView2);
        TextView ano1 = findViewById(R.id.textView3);
        TextView genero1 = findViewById(R.id.textView4);
        ImageView faixa1 = findViewById(R.id.textView5);

        nome1.setText("Nome: " + nome.toString());
        diretor1.setText("Diretor: " + diretor.toString());
        ano1.setText("Ano: " + ano.toString());
        genero1.setText("Gênero: " + genero.toString());
        if (faixa.toString().equals("Livre")) {
            faixa1.setImageResource(R.drawable.livre_icon);
        } else if (faixa.toString().equals("10 anos")) {
            faixa1.setImageResource(R.drawable.idade10);
        } else if (faixa.toString().equals("12 anos")) {
            faixa1.setImageResource(R.drawable.idade12);
        } else if (faixa.toString().equals("14 anos")) {
            faixa1.setImageResource(R.drawable.idade14);
        } else if (faixa.toString().equals("16 anos")) {
            faixa1.setImageResource(R.drawable.idade16);
        } else if(faixa.toString().equals("18 anos")){
            faixa1.setImageResource(R.drawable.idade18);
        }

        FloatingActionButton del = findViewById(R.id.floatingActionButton);

        del.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {

                                       AlertDialog.Builder builder = new AlertDialog.Builder(TelaFilme.this);
                                       builder.setTitle("Excluir o filme");
                                       builder.setMessage("Tem certeza que quer excluir este filme?");

                                       builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                           public void onClick(DialogInterface arg0, int arg1) {
                                               crud.deletaRegistro(id);
                                               Intent intent = new Intent(TelaFilme
                                                       .this, MainActivity.class);
                                               startActivity(intent);
                                               Toast toast = Toast.makeText(TelaFilme.this, "Excluído com sucesso", Toast.LENGTH_SHORT);
                                               toast.show();

                                           }
                                       });

                                       builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                           public void onClick(DialogInterface arg0, int arg1) {
                                           }
                                       });

                                       alerta = builder.create();
                                       alerta.show();
                                   }
                               });

        FloatingActionButton share =findViewById(R.id.floatingActionButton1);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Título: " + nome + "\nDiretor: " + diretor + "\nAno: " + ano + "\nGênero: " + genero + "\nFaixa: " + faixa);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }
}
