/*
 * Copyright (c) 2015. Diederich Kroeske - dkroeske@gmail.com -
 */

package com.appsfromholland.track_3_sqlite_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by dkroeske on 9/9/15.
 */
public class PersonDBHandler extends SQLiteOpenHelper {

    private static final String TAG = "PersonDBHandler";

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "person.db";
    private static final String DB_TABLE_NAME = "persons";

    // Tabel en kolom namen ...
    private static final String COLUMN_ID = "_id";  // primary key, auto increment
    private static final String COLUMN_FIRSTNAME = "firstName";
    private static final String COLUMN_LASTNAME = "lastName";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_IMAGEURL = "imageUrl";

    // Default constructor
    public PersonDBHandler(Context context, String name,
                           SQLiteDatabase.CursorFactory factory,
                           int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    // Als de db niet bestaat wordt de db gemaakt. In de onCreate() de query
    // voor de aanmaak van de database
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Creating NEW table " + DB_TABLE_NAME);
        String CREATE_PERSON_TABLE = "CREATE TABLE " + DB_TABLE_NAME +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_FIRSTNAME + " TEXT," +
                COLUMN_LASTNAME + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_AGE + " INTEGER," +
                COLUMN_IMAGEURL + " TEXT" +
                ")";
        db.execSQL(CREATE_PERSON_TABLE);
    }

    // Bij verandering (verhoging van version nr) van de db wordt onUpgrade aangeroepen.
    // Je kiest zelf wat je hier doet - wij verwijderen de oude en installeren de nieuwe db.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade - from " + oldVersion + " to " + newVersion + " - DROPPING EXISTING DATABASE");
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
        onCreate(db);
    }

    // Bij downgraden (verlaging van version nr) van de db wordt onDowngrade aangeroepen.
    // Je kiest zelf wat je hier doet - wij verwijderen de oude en installeren de nieuwe db.
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onDowngrade - from " + oldVersion + " to " + newVersion + " - DROPPING EXISTING DATABASE");
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
        onCreate(db);
    }

    // CRUD functies hier ....
    public void addPerson(Person person)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, person.getFirstName());
        values.put(COLUMN_LASTNAME, person.getLastName());
        values.put(COLUMN_EMAIL, person.getEmail());
        values.put(COLUMN_AGE, person.getAge());
        values.put(COLUMN_IMAGEURL, person.getImageUrl());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DB_TABLE_NAME, null, values);
        db.close();
    }

    public void getPersonByFirstName(String firstName) {

        String query_a = "SELECT * FROM " + DB_TABLE_NAME + " WHERE " +
                COLUMN_FIRSTNAME + "=" + "\"" + firstName + "\"";

        String query_b = "SELECT * FROM " + DB_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query_b, null);

        cursor.moveToFirst();
        while(cursor.moveToNext() ) {
            Log.i(TAG, cursor.getString(   cursor.getColumnIndex(COLUMN_FIRSTNAME)));
            Log.i(TAG, cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)));
            Log.i(TAG, cursor.getString(cursor.getColumnIndex(COLUMN_AGE)));
            Log.i(TAG, cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
            Log.i(TAG, cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEURL)));
            Log.i(TAG, "--------------------------------------------");
        }

        db.close();
    }

    public void findAllPersons() {

        String query = "SELECT * FROM " + DB_TABLE_NAME;
        Log.i(TAG, "findAllPersons - " + query);
        Log.i(TAG, "--------------------------------------------");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Log.i(TAG, cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)));
                Log.i(TAG, cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)));
                Log.i(TAG, cursor.getString(cursor.getColumnIndex(COLUMN_AGE)));
                Log.i(TAG, cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                Log.i(TAG, cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEURL)));
                Log.i(TAG, "--------------------------------------------");
            } while (cursor.moveToNext());
        }

        db.close();
    }

}
