package zen.trabalhotp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NewDB extends SQLiteOpenHelper {
    public static final String NAME_BASE = "DataBase.db";
    public static final String TABLE = "Movies";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String GENRE = "genre";
    public static final String DIRECTOR = "director";
    public static final String RATINGRANGE = "ratingRange";
    public static final String YEAR = "year";
    public static final int VERSION = 1;

    public NewDB(Context context){
        super(context, NAME_BASE,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + "("
                + ID + " integer primary key autoincrement,"
                + NAME + " text,"
                + GENRE + " text,"
                + DIRECTOR + " text,"
                + RATINGRANGE + " integer,"
                + YEAR + " integer"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
