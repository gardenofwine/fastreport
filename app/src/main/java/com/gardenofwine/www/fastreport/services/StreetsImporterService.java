package com.gardenofwine.www.fastreport.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.gardenofwine.www.fastreport.db.StreetsImporter;

/**
 * Created by ifeins on 2/24/15.
 */
public class StreetsImporterService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public StreetsImporterService() {
        super(StreetsImporterService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Context baseContext = getBaseContext();
        StreetsImporter importer = new StreetsImporter(baseContext);

        importer.importStreetsFromCSV();
    }

}
