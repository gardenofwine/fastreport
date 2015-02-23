package com.gardenofwine.www.fastreport.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gardenofwine.www.fastreport.db.models.Street;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by ifeins on 2/18/15.
 */
public class FastReportDBHelper extends OrmLiteSqliteOpenHelper {

    private static final String LOG_TAG = FastReportDBHelper.class.getSimpleName();

    private static final String DB_NAME = "fastreport.db";

    private static final int DB_VERSION = 1;

    public FastReportDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Street.class);
        } catch (SQLException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

}
