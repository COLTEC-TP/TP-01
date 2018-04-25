package com.example.gui.tp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Guilherme Assis on 2016-05-13.
 */
public class bancoDeDados extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "filmes.db";
    public static final String TABLE_NAME = "filmes_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "NOME";
    public static final String COL3 = "GENERO";
    public static final String COL4 = "DIRETOR";
    public static final String COL5 = "ANO";
    public static final String COL6 = "FAIXA";


    public bancoDeDados(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " NOME TEXT, GENERO TEXT, DIRETOR TEXT, ANO TEXT, FAIXA TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String fNome, String fGenero, String fDiretor, String fAno, String fFaixa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, fNome);
        contentValues.put(COL3, fGenero);
        contentValues.put(COL4, fDiretor);
        contentValues.put(COL5, fAno);
        contentValues.put(COL6, fFaixa);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //retornar -1 se foi inserido um dado incorretamente
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //pegar dados
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}
