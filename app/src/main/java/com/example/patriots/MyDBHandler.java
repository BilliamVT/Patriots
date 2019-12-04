package com.example.patriots;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import com.example.patriots.dummy.PatriotsPlayerContent;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "players.db";
    public static final String TABLE_PLAYERS = "players";
    public static final String COLUMN_ID = "playerID";
    public static final String COLUMN_NAME = "playerName";
    public static final String COLUMN_NUMBER = "playerNumber";
    public static final String COLUMN_POSITION = "playerPosition";
    public static final String COLUMN_AGE = "playerAge";
    public static final String COLUMN_COLLEGE = "playerCollege";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PLAYERS + "(" +
                COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_NUMBER + " INTEGER, " +
                COLUMN_POSITION + " TEXT, " +
                COLUMN_AGE + " INTEGER, " +
                COLUMN_COLLEGE + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        onCreate(db);
    }

    public void addPlayer(PatriotsPlayerContent.PatriotsPlayer player) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, player.getName());
        values.put(COLUMN_NUMBER, player.getNumber());
        values.put(COLUMN_POSITION, player.getPosition());
        values.put(COLUMN_AGE, player.getAge());
        values.put(COLUMN_COLLEGE, player.getCollege());

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_PLAYERS, null, values);

        db.close();

    }

    public void deletePlayer(String playerName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PLAYERS + " WHERE " + COLUMN_NAME + "=\"" + playerName + "\";");
    }

    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PLAYERS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("playerName")) != null) {
                dbString += c.getString(c.getColumnIndex("playerName"));
                dbString += "\n";
            }
        }

        db.close();
        return dbString;

    }


}
