package com.example.gui.tp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private static int tempo = 34230; //Em milissegundos - 34,23 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // tela cheia
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Retirar action Bar
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);



        //Chama Splash Screen
        Intent intent = new Intent(MainActivity.this, splashActivity.class);
        startActivity(intent);

        //Toca audio da splash screen (barulho de teclas)
        MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.audioteclado);
        ring.start();


        //Chama a listagem de filmes depois de 34 segundos (quando acabar a splashscreen
        new Handler().postDelayed(new Runnable() {
        @Override
            public void run() {
                Intent intent2 = new Intent(MainActivity.this, listarFilmes.class);
                startActivity(intent2);
            }
        }, tempo);

    }
}
