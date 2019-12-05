package com.example.patriots;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import com.example.patriots.dummy.PatriotsPlayerContent;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "players.db";
    public static final String TABLE_PLAYERS = "players";
    public static final String COLUMN_ID = "playerID";
    public static final String COLUMN_NAME = "playerName";
    public static final String COLUMN_NUMBER = "playerNumber";
    public static final String COLUMN_POSITION = "playerPosition";
    public static final String COLUMN_AGE = "playerAge";
    public static final String COLUMN_COLLEGE = "playerCollege";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creates the db
        String query = "CREATE TABLE " + TABLE_PLAYERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_NUMBER + " TEXT, " +
                COLUMN_POSITION + " TEXT, " +
                COLUMN_AGE + " TEXT, " +
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

        // columns of db
        values.put(COLUMN_NAME, player.getName());
        values.put(COLUMN_NUMBER, player.getNumber());
        values.put(COLUMN_POSITION, player.getPosition());
        values.put(COLUMN_AGE, player.getAge());
        values.put(COLUMN_COLLEGE, player.getCollege());

        // get the db
        SQLiteDatabase db = getWritableDatabase();

        // insert the information
        db.insert(TABLE_PLAYERS, null, values);

        // close the db
        db.close();

    }

    public void deletePlayer(String playerName) {
        // get the db
        SQLiteDatabase db = getWritableDatabase();

        // delete the player with given player name
        db.execSQL("DELETE FROM " + TABLE_PLAYERS + " WHERE " + COLUMN_NAME + "=\"" + playerName + "\";");

        // run the function to delete player from the ITEMS list
        PatriotsPlayerContent.deletePlayerByName(playerName);
    }

    public PatriotsPlayerContent.PatriotsPlayer getPlayer(String playerName) {
        // get the db
        SQLiteDatabase db = getReadableDatabase();

        // get the table
        String query = "SELECT * FROM " + TABLE_PLAYERS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);

        // set cursor to the first element
        c.moveToFirst();

        // loop through db
        while (!c.isAfterLast()) {
            // if we find the player name
            if (c.getString(c.getColumnIndex(COLUMN_NAME)).equals(playerName)) {
                // return the player
                return new PatriotsPlayerContent.PatriotsPlayer(
                        c.getString(c.getColumnIndex(COLUMN_NAME)),
                        c.getString(c.getColumnIndex(COLUMN_NUMBER)),
                        c.getString(c.getColumnIndex(COLUMN_POSITION)),
                        c.getString(c.getColumnIndex(COLUMN_AGE)),
                        c.getString(c.getColumnIndex(COLUMN_COLLEGE)));
            }
            // move to next element
            c.moveToNext();
        }

        // close db
        db.close();

        // else return null
        return null;
    }

    public ArrayList<PatriotsPlayerContent.PatriotsPlayer> getListOfPlayers() {
        // arraylist for the players
        ArrayList<PatriotsPlayerContent.PatriotsPlayer> players = new ArrayList<>();

        // get the db
        SQLiteDatabase db = getWritableDatabase();

        // get the whole tables
        String query = "SELECT * FROM " + TABLE_PLAYERS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);

        // set cursor at first element
        c.moveToFirst();

        // loop through table
        while (!c.isAfterLast()) {
            // as long as we are not at the end
            if (c.getString(c.getColumnIndex("playerName")) != null) {
                // add player to arraylist
                players.add(new PatriotsPlayerContent.PatriotsPlayer(
                       c.getString(c.getColumnIndex(COLUMN_NAME)),
                       c.getString(c.getColumnIndex(COLUMN_NUMBER)),
                       c.getString(c.getColumnIndex(COLUMN_POSITION)),
                       c.getString(c.getColumnIndex(COLUMN_AGE)),
                       c.getString(c.getColumnIndex(COLUMN_COLLEGE))));
            }
            // move to next element
            c.moveToNext();
        }

        // close db
        db.close();

        // return arraylist
        return players;
    }

}
