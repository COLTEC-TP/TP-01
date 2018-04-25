package com.ufmg.dener.movieme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddMovie extends AppCompatActivity {

    public static String[] genres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        genres = getResources().getStringArray(R.array.movie_choose_genre);


        final EditText name = findViewById(R.id.name);
        final Spinner genre = findViewById((R.id.genre));
        final EditText autor = findViewById(R.id.autor);
        final EditText date = findViewById(R.id.date);
        final Spinner age = findViewById(R.id.age);

        ArrayAdapter<String> adapterAge = new ArrayAdapter<>(AddMovie.this,
                android.R.layout.simple_spinner_item, MovieContract.AGES);
        adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(adapterAge);

        ArrayAdapter<String> adapterGenre = new ArrayAdapter<>(AddMovie.this,
                android.R.layout.simple_spinner_item, genres);
        adapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genre.setAdapter(adapterGenre);

        Button saveChanges = findViewById(R.id.save);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseController crud = new DataBaseController(getBaseContext());

                boolean result =
                        crud.insert(new Movie( name.getText().toString(),
                                autor.getText().toString(),
                                genre.getSelectedItem().toString(),
                                age.getSelectedItemPosition(),
                                Integer.parseInt(date.getText().toString())));

                String print = result ? getResources().getString(R.string.done) : getResources().getString(R.string.failed);

                Toast.makeText(getApplicationContext(), print, Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
