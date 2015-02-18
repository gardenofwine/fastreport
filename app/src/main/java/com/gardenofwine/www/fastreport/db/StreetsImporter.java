package com.gardenofwine.www.fastreport.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Created by ifeins on 2/18/15.
 */
public class StreetsImporter {

    private static final String LOG_TAG = StreetsImporter.class.getSimpleName();

    public static final String FILE_NAME = "tel_aviv_streets.csv";
    private final Context context;
    private final FastReportDBHelper dbHelper;

    public StreetsImporter(Context context) {
        this.context = context;
        this.dbHelper = new FastReportDBHelper(context);
    }

    public void importStreetsFromCSV() {
        try {
            InputStreamReader reader = new InputStreamReader(context.getAssets().open(FILE_NAME));
            CSVReader csvReader = new CSVReader(reader);
            int streetsAdded = 0;
            Log.d(LOG_TAG, "Beginning importing streets");
            String[] nextLine = csvReader.readNext();
            while (nextLine != null) {
                boolean added = addStreet(nextLine);
                if (added) {
                    streetsAdded++;
                }
                nextLine = csvReader.readNext();
            }
            Log.d(LOG_TAG, "Imported streets: " + streetsAdded);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean addStreet(String[] csvRow) {
        String streetCode = csvRow[0].trim();
        if (doesStreetExist(streetCode)) {
            return false;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("street_name", csvRow[3].trim());
        values.put("english_street_name", csvRow[4].trim());
        values.put("street_code", streetCode);
        values.put("first_apartment_number", csvRow[1].trim());
        values.put("last_apartment_number", csvRow[2].trim());
        db.insert("streets", null, values);

        return true;
    }

    private boolean doesStreetExist(String streetCode) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("streets", null, "street_code = ?", new String[] {streetCode},
                null, null, null);
        try {
            if (cursor.moveToFirst()) {
                return true;
            } else {
                return false;
            }
        } finally {
            cursor.close();
            db.close();
        }
    }
}
