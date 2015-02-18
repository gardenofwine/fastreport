package com.gardenofwine.www.fastreport.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ifeins on 2/18/15.
 */
public class FastReportDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "fastreport.db";

    private static final int DB_VERSION = 2;

    public FastReportDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_STREETS_SQL = "CREATE TABLE streets (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "street_name TEXT NOT NULL, " +
                "english_street_name TEXT, " +
                "street_code INTEGER NOT NULL UNIQUE, " +
                "first_apartment_number INTEGER, " +
                "last_apartment_number INTEGER)";

        db.execSQL(CREATE_STREETS_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertStreet(String streetName, String englishStreetName, int streetCode,
                             int firstNumber, int secondNumber) {
    }
}
