package com.ufmg.dener.movieme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Dener on 15/04/2018.
 */

public class DataBaseController {

    private SQLiteDatabase db;
    private DataBase dataBase;
    private final String[] values = {DataBase.NAME, DataBase.GENRE, DataBase.AUTOR, DataBase.DATE, DataBase.AGE, DataBase.ID};

    public DataBaseController(Context context) {
        dataBase = new DataBase(context);
    }

    public boolean insert(Movie movie) {
        ContentValues values;
        long result;

        this.db = this.dataBase.getWritableDatabase();
        values = new ContentValues();
        values.put(DataBase.NAME, movie.getName());
        values.put(DataBase.AUTOR, movie.getDirector());
        values.put(DataBase.GENRE, movie.getGenre());
        values.put(DataBase.AGE, movie.getAge());
        values.put(DataBase.DATE, movie.getDate());

        result = this.db.insert(DataBase.TABLE, null, values);
        this.db.close();

        return (result != -1);
    }

    public boolean update(Movie movie, int id) {

        ContentValues values;
        String where = DataBase.ID + " = " + id;

        db = this.dataBase.getWritableDatabase();

        values = new ContentValues();
        values.put(DataBase.NAME, movie.getName());
        values.put(DataBase.AUTOR, movie.getDirector());
        values.put(DataBase.GENRE, movie.getGenre());
        values.put(DataBase.AGE, movie.getAge());
        values.put(DataBase.DATE, movie.getDate());

        int error = db.update(DataBase.TABLE, values, where, null);
        db.close();

        System.out.println(error);
        System.out.println(id);

        return (error != -1);
    }


    public ArrayList<Movie> loadData() {
        ArrayList<Movie> movies = new ArrayList<>();
        Movie movie;
        this.db = this.dataBase.getReadableDatabase();
        Cursor cursor = this.db.query(DataBase.TABLE, values, null, null, null, null, null, null);

        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                int idName = cursor.getColumnIndexOrThrow(DataBase.NAME);
                int idAutor = cursor.getColumnIndexOrThrow(DataBase.AUTOR);
                int idGenre = cursor.getColumnIndexOrThrow(DataBase.GENRE);
                int idDate = cursor.getColumnIndexOrThrow(DataBase.DATE);
                int idAge = cursor.getColumnIndexOrThrow(DataBase.AGE);
                int idId = cursor.getColumnIndexOrThrow(DataBase.ID);

                String name = cursor.getString(idName);
                String autor = cursor.getString(idAutor);
                String genre = cursor.getString(idGenre);
                int date = cursor.getInt(idDate);
                int age = cursor.getInt(idAge);

                movie = new Movie(name, autor, genre, age, date);
                movie.setId(cursor.getInt(idId));
                movies.add(movie);
            }
        }
        this.db.close();
        return movies;
    }

    public Movie loadData(int id) {
        Movie movie;
        String where = DataBase.ID + " = " + id;
        this.db = this.dataBase.getReadableDatabase();
        Cursor cursor = this.db.query(DataBase.TABLE, values, where, null, null, null, null, null);

        int idName = cursor.getColumnIndexOrThrow(DataBase.NAME);
        int idAutor = cursor.getColumnIndexOrThrow(DataBase.AUTOR);
        int idGenre = cursor.getColumnIndexOrThrow(DataBase.GENRE);
        int idDate = cursor.getColumnIndexOrThrow(DataBase.DATE);
        int idAge = cursor.getColumnIndexOrThrow(DataBase.AGE);
        int idId = cursor.getColumnIndexOrThrow(DataBase.ID);

        cursor.moveToFirst();

        String name = cursor.getString(idName);
        String autor = cursor.getString(idAutor);
        String genre = cursor.getString(idGenre);
        int date = cursor.getInt(idDate);
        int age = cursor.getInt(idAge);

        movie = new Movie(name, autor, genre, age, date);
        movie.setId(cursor.getInt(idId));

        this.db.close();
        return movie;
    }

    public void delete(int id) {
        String where = DataBase.ID + " = " + id;
        db = dataBase.getReadableDatabase();
        db.delete(DataBase.TABLE, where, null);
        db.close();
    }
}
