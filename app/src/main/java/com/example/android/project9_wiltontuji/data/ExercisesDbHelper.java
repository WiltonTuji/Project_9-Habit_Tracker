package com.example.android.project9_wiltontuji.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.StringBuilderPrinter;

import static com.example.android.project9_wiltontuji.data.ExercisesContract.ExercisesEntry.COLUMN_EXERCISE;
import static com.example.android.project9_wiltontuji.data.ExercisesContract.ExercisesEntry.COLUMN_QUANTITY;
import static com.example.android.project9_wiltontuji.data.ExercisesContract.ExercisesEntry.TABLE_NAME;
import static com.example.android.project9_wiltontuji.data.ExercisesContract.ExercisesEntry._ID;

/**
 * Created by Adailto on 26/05/2017.
 */

public class ExercisesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "exercises.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_EXERCISE + " TEXT NOT NULL, " + COLUMN_QUANTITY + " INTEGER NOT NULL)" ;
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public ExercisesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
