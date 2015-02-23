package com.gardenofwine.www.fastreport.db;

import android.content.Context;
import android.util.Log;

import com.gardenofwine.www.fastreport.db.models.Street;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Created by ifeins on 2/18/15.
 */
public class StreetsImporter {

    private static final String LOG_TAG = StreetsImporter.class.getSimpleName();

    public static final String FILE_NAME = "tel_aviv_streets.csv";
    private final Context context;

    public StreetsImporter(Context context) {
        this.context = context;
    }

    public void importStreetsFromCSV() {
        FastReportDBHelper dbHelper = OpenHelperManager.getHelper(context, FastReportDBHelper.class);
        final RuntimeExceptionDao<Street, Integer> dao = dbHelper.getRuntimeExceptionDao(Street.class);
        ConnectionSource connectionSource = dbHelper.getConnectionSource();

        try {
            TransactionManager.callInTransaction(connectionSource, new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    CSVReader csvReader = null;
                    try {
                        csvReader = new CSVReader(new InputStreamReader(context.getAssets().open(FILE_NAME)));
                        int streetsAdded = 0;
                        Log.d(LOG_TAG, "Beginning importing streets");
                        String[] nextLine = csvReader.readNext();
                        while (nextLine != null) {
                            boolean added = addStreet(dao, nextLine);
                            if (added) streetsAdded++;
                            nextLine = csvReader.readNext();
                        }
                        Log.d(LOG_TAG, "Imported streets: " + streetsAdded);
                    } catch (IOException e) {
                        Log.e(LOG_TAG, e.getMessage(), e);
                    } finally {
                        if (csvReader != null) {
                            csvReader.close();
                        }
                    }

                    return null;
                }
            });
        } catch (SQLException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        } finally {
            OpenHelperManager.releaseHelper();
        }
    }

    private boolean addStreet(RuntimeExceptionDao<Street, Integer> dao, String[] csvRow) {
        String streetCode = csvRow[0].trim();
        if (doesStreetExist(dao, streetCode)) {
            return false;
        }

        Street street = new Street(csvRow[3].trim(), csvRow[4].trim(), Integer.parseInt(streetCode),
                Integer.parseInt(csvRow[1].trim()),
                Integer.parseInt(csvRow[2].trim()));

        dao.create(street);
        return true;
    }

    private boolean doesStreetExist(RuntimeExceptionDao<Street, Integer> dao, String streetCode) {
        List<Street> streets = dao.queryForEq("street_code", streetCode);
        return !streets.isEmpty();
    }
}
