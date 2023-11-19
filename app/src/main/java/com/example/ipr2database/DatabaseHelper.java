package com.example.ipr2database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE = "Persons";
    public static final String ID = "_id";
    public static final String NAME = "_name";
    public static final String POST = "_post";
    public static final String SALARY = "_salary";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Users.db";

    public static final String TO_SQL_CREATE =
            "CREATE TABLE " + TABLE + " (" +
                    ID + " INTEGER PRIMARY KEY," +
                    NAME + " TEXT," +
                    POST + " TEXT," +
                    SALARY  + " INTEGER)";

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

    public void addToDatabase(String name, String post, int salary) {
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(POST, post);
        cv.put(SALARY, salary);
        database.insert(TABLE, null, cv);
    }

    public void clearDb() {
        for(int i = 0; i < 100; i++) {
            database.delete(TABLE,ID + "=" + i, null);
        }
    }

    @SuppressLint("Range")
    public List<Person> readDatabase() {
        List<Person> list = new ArrayList<>();
        Cursor c = database.query(TABLE,null,null,null,
                null, null, null);
        while (c.moveToNext()) {
            String id = c.getString(c.getColumnIndex(ID));
            String name = c.getString(c.getColumnIndex(NAME));
            String post = c.getString(c.getColumnIndex(POST));
            int salary = c.getInt(c.getColumnIndex(SALARY));
            Person person = new Person(name, post, salary);
            list.add(person);
        }
        c.close();
        return list;
    }

    @SuppressLint("Range")
    public Person takeFromDatabase(int id) {
        Person person = new Person(null,null,0);
        Cursor c = database.query(TABLE,null,null,null,
                null, null, null);
        while (c.moveToNext()) {
            int thisId = c.getInt(c.getColumnIndex(ID));
            if (id == thisId) {
                String name = c.getString(c.getColumnIndex(NAME));
                String post = c.getString(c.getColumnIndex(POST));
                int salary = c.getInt(c.getColumnIndex(SALARY));
                person = new Person(name, post, salary);
                break;
            }
        }
        c.close();
        return person;
    }

    @SuppressLint("Range")
    public void deleteFromDatabase(String name) {
        int id = 0;
        Cursor c = database.query(TABLE,null,null,null,
                null, null, null);
        while (c.moveToNext()) {
            String thisUsername = c.getString(c.getColumnIndex(NAME));
            if(name.equals(thisUsername)) {
                id = c.getInt(c.getColumnIndex(ID));
                database.delete(TABLE,ID + "=" + id, null);
                break;
            }
        }
        c.close();
        ContentValues cv = new ContentValues();
        Cursor cursor = database.query(TABLE,null,null,null,
                null, null, null);
        while (cursor.moveToNext()) {
            int i = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)));
            if(i > id) {
                cv.put(ID, i - 1);
                database.update(TABLE, cv, ID + "=" + i, null);
            }
        }
        cursor.close();
    }


    @SuppressLint("Range")
    public void updateDatabase(String oldName, String newName, String post, int salary) {
        Cursor c = database.query(TABLE,null,null,null,
                null, null, null);
        while (c.moveToNext()) {
            String thisUsername = c.getString(c.getColumnIndex(NAME));
            if(oldName.equals(thisUsername)) {
                int id = c.getInt(c.getColumnIndex(ID));
                ContentValues cv = new ContentValues();
                cv.put(NAME, newName);
                cv.put(POST, post);
                cv.put(SALARY, salary);
                database.update(TABLE, cv, ID + "=" + id, null);
                break;
            }
        }
        c.close();
    }

    public void closeDatabase() {
        this.close();
    }
}