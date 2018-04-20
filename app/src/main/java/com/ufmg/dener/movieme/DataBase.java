package com.ufmg.dener.movieme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dener on 15/04/2018.
 */

public class DataBase extends SQLiteOpenHelper {

    public static final String DATA_BASE = "banco.db";
    public static final String ID = "_id";
    public static final String TABLE = "movies";
    public static final String NAME = "name";
    public static final String GENRE = "genre";
    public static final String AUTOR = "autor";
    public static final String DATE = "date";
    public static final String AGE = "age";
    public static final int VERSION = 1;

    public DataBase(Context context){
        super(context, DATA_BASE,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlite = "CREATE TABLE "+TABLE+"("
                + ID + " integer primary key autoincrement,"
                + NAME + " text,"
                + GENRE + " text,"
                + AUTOR + " text,"
                + DATE + " integer,"
                + AGE + " integer"
                +")";
        db.execSQL(sqlite);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

}
