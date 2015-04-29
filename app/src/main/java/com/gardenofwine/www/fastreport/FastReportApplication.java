package com.gardenofwine.www.fastreport;

import android.app.Application;
import android.content.Intent;

import com.gardenofwine.www.fastreport.services.StreetsImporterService;

/**
 * Created by ifeins on 2/24/15.
 */
public class FastReportApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        startService(new Intent(this, StreetsImporterService.class));
    }
}
