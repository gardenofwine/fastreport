/*
 * Copyright (c) 2015 PayPal, Inc.
 *
 * All rights reserved.
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 */

package com.gardenofwine.www.fastreport.db.dao;

import android.util.Log;

import com.gardenofwine.www.fastreport.db.models.Street;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * DAO for fetching streets.
 * @author ifeins
 */
public class StreetDao extends BaseCustomDao<Street, Integer> {

    private static final String LOG_TAG = StreetDao.class.getSimpleName();

    public StreetDao(Class<Street> dataClass) throws SQLException {
        super(dataClass);
    }

    public StreetDao(ConnectionSource connectionSource, Class<Street> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public StreetDao(ConnectionSource connectionSource, DatabaseTableConfig<Street> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    public List<Street> filterStreets(String prefix) {
        try {
            return queryBuilder().where().like("street_name", "%" + prefix + "%").query();
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Encountered error while trying to filter streets", e);
        }

        return Collections.emptyList();
    }

    public Street findStreetByName(String name) {
        SelectArg selectArg = new SelectArg(name);
        try {
            return queryBuilder().where().eq("street_name", selectArg).queryForFirst();
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Encountered error while trying to find street", e);
        }

        return null;
    }

    public List<Street> findStreetsByStreetCode(int streetCode) {
        try {
            return queryBuilder().where().eq("street_code", streetCode).query();
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Encountered error while trying to find streets", e);
        }

        return Collections.emptyList();
    }
}
