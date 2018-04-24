package br.ufmg.coltec.teste;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by a2016951782 on 19/04/18.
 */



public class CriaBanco extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "filmes";
    public static final String ID = "_id";
    public static final String NOME_FILME = "nome";
    public static final String DIRETOR = "diretor";
    public static final String ANO = "ano";
    public static final String GENERO = "genero";
    public static final String FAIXA = "faixa";
    public static final int VERSAO = 1;

    public CriaBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "+TABELA+" ("
                + ID + " integer primary key autoincrement,"
                + NOME_FILME + " text,"
                + DIRETOR + " text,"
                + ANO + " text,"
                + GENERO + " text,"
                + FAIXA + " text"
                +")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int novo) {

        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);

    }
}

