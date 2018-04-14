package com.example.haasi.tp_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class FilmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);

//        Log.d("GRR", "created");
//        Integer id = getIntent().getIntExtra("id", 0);
//        Log.d("GRR", id.toString());
//        Filme filme = MainActivity.dao.retornaFilme(((int) id));
//        Toast.makeText(this, filme.getMovie(), Toast.LENGTH_LONG);
    }

    protected void onStart(){
        super.onStart();
        Log.d("GRR", "created");
        Integer id = getIntent().getIntExtra("id", 0);
        Log.d("GRR", id.toString());
        Filme filme = MainActivity.dao.retornaFilme(((int) id));
        Toast.makeText(this, filme.getMovie(), Toast.LENGTH_LONG);

    }


}
