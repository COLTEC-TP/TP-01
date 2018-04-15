package zen.trabalhotp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBcontroller {

    private SQLiteDatabase db;
    private NewDB newBase;

    public DBcontroller(Context context) {
        this.newBase = new NewDB(context);
    }

    public String addData(String name, String genre, String director, Integer ratingRange, Integer year){
        ContentValues valores = new ContentValues();
        db = newBase.getWritableDatabase();
        long resultado;

        valores.put(NewDB.NAME, name);
        valores.put(NewDB.GENRE, genre);
        valores.put(NewDB.DIRECTOR, director);
        valores.put(NewDB.RATINGRANGE, ratingRange);
        valores.put(NewDB.YEAR, year);

        resultado = db.insert(NewDB.TABLE, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public Cursor uploadData(){
        Cursor cursor;
        String[] data = {newBase.ID, newBase.NAME, newBase.GENRE, newBase.DIRECTOR, newBase.RATINGRANGE, newBase.YEAR};
        db = newBase.getReadableDatabase();
        cursor = db.query(newBase.TABLE, data, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public void deleteData(Integer id){
        String where = NewDB.ID + "=" + id;
        db = newBase.getReadableDatabase();
        db.delete(newBase.TABLE,where,null);
        db.close();
    }
}

