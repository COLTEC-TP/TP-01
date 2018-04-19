package com.example.haasi.tp_01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by riversong on 07/04/18.
 */

public class DbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Filmes.db";
    private final String CREATE_TABLE = "CREATE TABLE Filmes(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT NOT NULL, Type TEXT NOT NULL, Rate INTEGER NOT NULL, Year TEXT, Director TEXT);";

    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        Log.d("debug", "Banco de dados foi criado");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}

