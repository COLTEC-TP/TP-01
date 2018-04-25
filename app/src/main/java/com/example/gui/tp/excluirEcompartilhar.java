package com.example.gui.tp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class excluirEcompartilhar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // tela cheia
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Retirar action Bar
        getSupportActionBar().hide();

        setContentView(R.layout.activity_excluir_ecompartilhar);


        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle(R.string.FORMULARIO_submit);
        alertBuilder.setMessage(R.string.FORMULARIO_areYouSure);

        alertBuilder.setPositiveButton(R.string.FORMULARIO_PositiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getBaseContext(), R.string.FORMULARIO_MensagemEnviado, Toast.LENGTH_LONG).show();
            }
        });

        alertBuilder.setNegativeButton(R.string.FORMULARIO_NegativeButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getBaseContext(), R.string.FORMULARIO_MensagemNaoEnviada, Toast.LENGTH_LONG).show();
            }
        });

        final AlertDialog dialog = alertBuilder.create();
        Button enviar = findViewById(R.id.excluir);
        enviar.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  dialog.show();
              }
          }
        );

        final AlertDialog dialog2 = alertBuilder.create();
        Button enviar2 = findViewById(R.id.compartilhar);
        enviar2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  dialog2.show();
              }
          }
        );

    }
}
