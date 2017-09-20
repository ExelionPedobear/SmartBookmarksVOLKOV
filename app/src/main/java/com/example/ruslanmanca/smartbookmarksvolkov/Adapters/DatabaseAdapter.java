package com.example.ruslanmanca.smartbookmarksvolkov.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public class DatabaseAdapter extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "smartbookmarksvolkov.db";

    Context newContext;

    public DatabaseAdapter(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.newContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE books(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title VARCHAR NOT NULL, " +
                "author VARCHAR NOT NULL, " +
                "genre VARCHAR NOT NULL )");

        db.execSQL("CREATE TABLE comments(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "bookId FLOAT NOT NULL, " +
                "comment FLOAT NOT NULL, " +
                "date FLOAT NOT NULL )");

        db.execSQL("INSERT INTO \"books\" VALUES(1,'Les fleurs du mal','Charles Baudelaire','Poèmes'); ");
        db.execSQL("INSERT INTO \"books\" VALUES(2,'Germinal','Emile Zola','Roman'); ");
        db.execSQL("INSERT INTO \"books\" VALUES(3,'Les misérables','Victor Hugo','Roman'); ");
        db.execSQL("INSERT INTO \"books\" VALUES(4,'1984','George Orwell','Science-Fiction'); ");
        db.execSQL("INSERT INTO \"books\" VALUES(5,'Le Meilleur des mondes','Aldous Huxley','Science-Fiction'); ");
        db.execSQL("INSERT INTO \"books\" VALUES(6,'Vingt mille lieues sous les mers','Jules Verne','Aventure'); ");
        db.execSQL("INSERT INTO \"books\" VALUES(7,'Les Trois Mousquetaires','Alexandre Dumas','Aventure'); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (newVersion) {
            case 2:

        }
    }
}
