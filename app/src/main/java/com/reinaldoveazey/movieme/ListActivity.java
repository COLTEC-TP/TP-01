package com.reinaldoveazey.movieme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends Activity {

    static final int REQUEST_MOVIE_CODE = 1;  // Código de receber novo movie
    private int modo=0; // 0 = modo em que o click abre tela de detales. 1 = modo em que o click remove o filme

    private void newMovie(){
        Intent intent = new Intent(ListActivity.this,NewMovieActivity.class);
        startActivityForResult(intent,REQUEST_MOVIE_CODE);
    }
    private void removerItem(int pos, MyAdapter adaptador, ListView moviesListView){
        String msgRem = adaptador.remover(pos).getName() + " " + getString(R.string.removido); //remover
        moviesListView.setAdapter(adaptador); //atualizar lista
        //Arquivo.salvarArquivo(adaptador.getMovies(),this); //Salvar modificações no arquivo
        Toast.makeText(ListActivity.this,msgRem,Toast.LENGTH_SHORT).show();
    }
    private void setModoLista(){
        TextView title = findViewById(R.id.title);
        title.setText(getString(R.string.Osseusfilmes));
        modo = 0;
    }
    private void setModoRemover(){
        TextView title = findViewById(R.id.title);
        title.setText(getString(R.string.Selecioneexcluir));
        modo = 1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);// Check which request we're responding to
        if (requestCode == REQUEST_MOVIE_CODE) {
            if (resultCode == RESULT_OK) {
                Movie movie = new Movie();
                movie.setName(data.getStringExtra("nome"));
                movie.setTarja(Integer.parseInt(data.getStringExtra("idade")));
                movie.setNameDir(data.getStringExtra("diretor"));
                movie.setGenero(data.getStringExtra("genero"));
                movie.setAno(Integer.parseInt(data.getStringExtra("ano")));

                //Agora vem a gambiarra básica
                ListView lista = findViewById(R.id.listmain);
                MyAdapter adaptador = (MyAdapter) lista.getAdapter();
                adaptador.adicionar(movie);
                //Arquivo.salvarArquivo(adaptador.getMovies(),this); //Salvar modificações no arquivo
                lista.setAdapter(adaptador); //atualizar
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add:
                setModoLista();
                newMovie();
                return true;
            case R.id.menu_remove:
                if(modo==0) setModoRemover();
                else if(modo==1) setModoLista();
                else Toast.makeText(ListActivity.this,getString(R.string.Algoerrado),Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_ajuda:
                AlertDialog.Builder dialogoB = new AlertDialog.Builder(this);
                dialogoB.setTitle(getString(R.string.Ajuda));
                dialogoB.setMessage(getString(R.string.textoAjuda));
                AlertDialog dialogo = dialogoB.create();
                dialogo.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // faz a ligação entre a ListView e o Adapter
        final ListView moviesListView = findViewById(R.id.listmain);
        final MyAdapter adaptador = new MyAdapter(this);
        moviesListView.setAdapter(adaptador);

        //Definir a ação de click
        moviesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Movie item = (Movie) adaptador.getItem(position); //Pega o filme que foi selecionado

                if(modo==0) { //O click abre tela de detales
                    Intent intent = new Intent(ListActivity.this, FilmeDetalesActivity.class);

                    Bundle args = new Bundle();
                    args.putCharSequence("tarja", item.getTarja().toString());
                    args.putCharSequence("name", item.getName());
                    args.putCharSequence("nameDir", item.getNameDir());
                    args.putCharSequence("genero", item.getGenero());
                    args.putCharSequence("ano", item.getAno().toString());
                    intent.putExtras(args);

                    startActivity(intent);
                }
                else if(modo==1){ //O click abre diálogo para excluir um filme
                    AlertDialog.Builder dialogoB = new AlertDialog.Builder(ListActivity.this);
                    String msg = getString(R.string.desejaremover) + " " + item.getName() + " " + getString(R.string.dasualista);
                    dialogoB.setTitle(getString(R.string.Remover));
                    dialogoB.setMessage(msg);
                    dialogoB.setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
                        //Remover e voltar para modo 0
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            removerItem(position,adaptador,moviesListView);
                            setModoLista();
                        }
                    });
                    dialogoB.setNegativeButton(getString(R.string.Cancelar), new DialogInterface.OnClickListener() {
                        //Voltar para modo 0
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ListActivity.this,getString(R.string.Nadaremovido),Toast.LENGTH_SHORT).show();
                            setModoLista();
                        }
                    });
                    dialogoB.setOnCancelListener(new DialogInterface.OnCancelListener() { //Quando o usuário aperta voltar ou fora do diálogo
                        //Fazer nada
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            Toast.makeText(ListActivity.this,getString(R.string.Selecioneexcluir),Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog dialogo = dialogoB.create();
                    dialogo.show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialogoB = new AlertDialog.Builder(ListActivity.this);
        dialogoB.setTitle(getString(R.string.sair));
        dialogoB.setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity(); //sair
            }
        });
        dialogoB.setNegativeButton(getString(R.string.nao), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //fazer nada
            }
        });
        AlertDialog dialogo = dialogoB.create();
        dialogo.show();
    }
}
