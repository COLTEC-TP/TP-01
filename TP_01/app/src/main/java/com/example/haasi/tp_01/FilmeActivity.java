package com.example.haasi.tp_01;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItem;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FilmeActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Integer id = getIntent().getIntExtra("id", 0);
        final Filme filme = MainActivity.dao.retornaFilme((id));

        TextView nome = findViewById(R.id.nome);
        TextView ano = findViewById(R.id.ano);
        TextView diretor = findViewById(R.id.diretor);
        ImageView icone = findViewById(R.id.icone);
        ImageView rating = findViewById(R.id.rate);

        nome.setText(filme.getMovie());
        ano.setText(filme.getYear());
        diretor.setText(filme.getDirector());

        if ((filme.getRate() % 6) == 1) {
            rating.setBackgroundResource(R.drawable.livre);
        } else if ((filme.getRate() % 6) == 2) {
            rating.setBackgroundResource(R.drawable.mais10);
        } else if ((filme.getRate() % 6) == 3) {
            rating.setBackgroundResource(R.drawable.mais12);
        } else if ((filme.getRate() % 6) == 4) {
            rating.setBackgroundResource(R.drawable.mais14);
        } else if ((filme.getRate() % 6) == 5) {
            rating.setBackgroundResource(R.drawable.mais16);
        } else if ((filme.getRate() % 6) == 0) {
            rating.setBackgroundResource(R.drawable.mais18);
        }

        if (filme.getType() == 0) {
            icone.setBackgroundResource(R.drawable.action);
        } else if (filme.getType() == 1) {
            icone.setBackgroundResource(R.drawable.drama);
        } else if (filme.getType() == 2) {
            icone.setBackgroundResource(R.drawable.comedy);
        } else if (filme.getType() == 3) {
            icone.setBackgroundResource(R.drawable.horror);
        } else if (filme.getType() == 4) {
            icone.setBackgroundResource(R.drawable.fiction);
        } else if (filme.getType() == 5) {
            icone.setBackgroundResource(R.drawable.love);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // infla menu na tela
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // recupera id do item selecionado
        int id = item.getItemId();
        Integer id1 = getIntent().getIntExtra("id", 0);
        final Filme filme = MainActivity.dao.retornaFilme((id1));

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setIcon(R.mipmap.clapperboard);
        alertBuilder.setTitle(getResources().getText(R.string.remove) + " " +filme.getMovie() + "?");
        alertBuilder.setMessage(getResources().getText(R.string.sure));

        alertBuilder.setPositiveButton(getResources().getText(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
             final public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.dao.deletar(filme.getId());
                finish();
            }
        });

        alertBuilder.setNegativeButton(getResources().getText(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        AlertDialog dialog = alertBuilder.create();


        // verifica qual é o botão selecionado com base no id
        switch (id) {
            case R.id.action_share:
                Intent myintent = new Intent(Intent.ACTION_SEND);
                myintent.setType("text/plain");
                String parte1= filme.getMovie();
                String parte2= filme.getDirector();
                String parte3= filme.getYear();
                String parte4= "";
                String parte5= "";

                if ((filme.getRate() % 6) == 1) {
                    parte4= "Livre";
                } else if ((filme.getRate() % 6) == 2) {
                    parte4= "Maiores de 10 anos";
                } else if ((filme.getRate() % 6) == 3) {
                    parte4= "Maiores de 12 anos";
                } else if ((filme.getRate() % 6) == 4) {
                    parte4= "Maiores de 14 anos";
                } else if ((filme.getRate() % 6) == 5) {
                    parte4= "Maiores de 16 anos";
                } else if ((filme.getRate() % 6) == 0) {
                    parte4= "Maiores de 18 anos";
                }

                if (filme.getType() == 0) {
                    parte5= "Ação";
                } else if (filme.getType() == 1) {
                    parte5= "Drama";
                } else if (filme.getType() == 2) {
                    parte5= "Comédia";
                } else if (filme.getType() == 3) {
                    parte5= "Suspense";
                } else if (filme.getType() == 4) {
                    parte5= "Ficção";
                } else if (filme.getType() == 5) {
                    parte5= "Romance";
                }

                myintent.putExtra(Intent.EXTRA_SUBJECT, parte1);
                myintent.putExtra(Intent.EXTRA_TEXT, parte1 + " -- " + parte2 + " -- " + parte3 + " -- " + parte4 + " -- " + parte5);
                startActivity(Intent.createChooser(myintent, getResources().getText(R.string.shareusing)));
                return true;
            case R.id.action_delete:
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}