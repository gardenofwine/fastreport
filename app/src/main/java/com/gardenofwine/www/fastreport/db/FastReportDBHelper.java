package com.gardenofwine.www.fastreport.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.common.io.CharStreams;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * Created by ifeins on 2/18/15.
 */
public class FastReportDBHelper extends OrmLiteSqliteOpenHelper {

    private static final String LOG_TAG = FastReportDBHelper.class.getSimpleName();

    private static final String DB_NAME = "fastreport.db";

    private static final int DB_VERSION = 2;

    private final Context context;

    public FastReportDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        migrate(database, 0, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        migrate(database, oldVersion, newVersion);
    }

    private void migrate(SQLiteDatabase database, int oldVersion, int newVersion) {
        for (int i = oldVersion + 1; i <= newVersion; i++) {
            String content = readMigrationContent(i);
            database.execSQL(content);
        }
    }

    private String readMigrationContent(int dbVersion) {
        InputStreamReader reader = null;
        try {
            String migrationPath = new StringBuilder("migrations/").append(dbVersion).append(".sql").toString();
            reader = new InputStreamReader(context.getAssets().open(migrationPath));
            return CharStreams.toString(reader);
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            throw new RuntimeException("Could not read migration for db version: " + dbVersion);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, e.getMessage(), e);
                    // not throwing exception so we were able to read the migration content
                }
            }
        }
    }

    @Override
    public <D extends Dao<T, ?>, T> D getDao(Class<T> clazz) {
        try {
            return super.getDao(clazz);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
