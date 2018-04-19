package com.example.haasi.tp_01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by riversong on 07/04/18.
 */

public class FilmeDAO {
    private final String TABLE_FILMES = "Filmes";
    private SQLiteDatabase gw;

    public FilmeDAO(Context ctx){
        gw = MainActivity.db;
    }


    // Adiciona o Filme na Data Base
    public boolean salvar(String movie, Integer type, Integer rate, String year, String director){

        ContentValues cv = new ContentValues();
        cv.put("Name", movie);
        cv.put("Type", type);
        cv.put("Rate", rate);
        cv.put("Year", year);
        cv.put("Director", director);
        return gw.insert(TABLE_FILMES, null, cv) > 0;
    }

    public boolean deletar(Integer id){
        return gw.delete(TABLE_FILMES, "ID=?", new String[]{ id + "" }) > 0;
    }

    public Filme retornaFilme(Integer identify){
        Cursor cursor = gw.rawQuery("SELECT * FROM Filmes", null);
        while(cursor.moveToNext()){
            if(cursor.getInt(cursor.getColumnIndex("ID")) == identify) {
                int id = cursor.getInt(cursor.getColumnIndex("ID"));
                String name = cursor.getString(cursor.getColumnIndex("Name"));
                Integer type = cursor.getInt(cursor.getColumnIndex("Type"));
                String year = cursor.getString(cursor.getColumnIndex("Year"));
                Integer rate = cursor.getInt(cursor.getColumnIndex("Rate"));
                String director = cursor.getString(cursor.getColumnIndex("Director"));
                cursor.close();
                return new Filme(id, name, type, rate, year, director);
            }
        }
        cursor.close();
        return null;
    }

    //Retorna todos os filmes em um array list
    public ArrayList<Filme> retornarTodos(){
        ArrayList<Filme> filmes = new ArrayList<>();
        Cursor cursor = gw.rawQuery("SELECT * FROM Filmes", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String name = cursor.getString(cursor.getColumnIndex("Name"));
            Integer type = cursor.getInt(cursor.getColumnIndex("Type"));
            String year = cursor.getString(cursor.getColumnIndex("Year"));
            Integer rate = cursor.getInt(cursor.getColumnIndex("Rate"));
            String director = cursor.getString(cursor.getColumnIndex("Director"));
            filmes.add(new Filme(id, name, type, rate, year, director));
        }
        cursor.close();
        return filmes;
    }
}