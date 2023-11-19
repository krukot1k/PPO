package com.example.kr2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE = "Users";
    public static final String ID = "_id";
    public static final String USERNAME = "_username";
    public static final String SCORE = "_score";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Users.db";

    public static final String TO_SQL_CREATE =
            "CREATE TABLE " + TABLE + " (" +
                    ID + " INTEGER PRIMARY KEY," +
                    USERNAME + " TEXT," +
                    SCORE  + " INTEGER)";

    public static final String TO_SQL_DELETE =
            "DROP TABLE IF EXISTS " + TABLE;

    public SQLiteDatabase database;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TO_SQL_CREATE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TO_SQL_DELETE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public void openDatabase() {
        database = this.getWritableDatabase();
    }

    public void addToDatabase(String username, int score) {
        ContentValues cv = new ContentValues();
        cv.put(USERNAME, username);
        cv.put(SCORE, score);
        database.insert(TABLE, null, cv);
    }

    @SuppressLint("Range")
    public User checkUser(String username) {
        int id;
        Cursor c = database.query(TABLE,null,null,null,
                null, null, null);
        while (c.moveToNext()) {
            String thisUsername = c.getString(c.getColumnIndex(USERNAME));
            if (username.equals(thisUsername)) {
                id = c.getInt(c.getColumnIndex(ID));
                int score = c.getInt(c.getColumnIndex(SCORE));
                return new User(id, username, score);
            }
        }
        c.close();
        return new User(-1, username, 0);
    }

    @SuppressLint("Range")
    public List<User> readDatabase() {
        List<User> list = new ArrayList<>();
        Cursor c = database.rawQuery("SELECT * FROM " + TABLE +
                " ORDER BY " + SCORE + " DESC;", null);
        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex(ID));
            String username = c.getString(c.getColumnIndex(USERNAME));
            int score = c.getInt(c.getColumnIndex(SCORE));
            User order = new User(id, username, score);
            list.add(order);
        }
        c.close();
        return list;
    }

    @SuppressLint("Range")
    public void updateDatabase(int id, String username, int score) {
        Cursor c = database.query(TABLE,null,null,null,
                null, null, null);
        while (c.moveToNext()) {
            String thisUsername = c.getString(c.getColumnIndex(USERNAME));
            if(username.equals(thisUsername)) {
                id = c.getInt(c.getColumnIndex(ID));
                ContentValues cv = new ContentValues();
                cv.put(SCORE, score);
                database.update(TABLE, cv, ID + "=" + id, null);
                break;
            }
        }
        c.close();
        if(id == -1) {
            addToDatabase(username, score);
        }
    }

    public void closeDatabase() {
        this.close();
    }
}