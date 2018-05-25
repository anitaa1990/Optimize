package com.an.optimize.rest;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class RestModule {

    private WorkManager workManager;
    protected RestModule() {
        workManager = WorkManager.getInstance();
    }

    protected void initialize(String url) {
        Data data = new Data.Builder()
                    .putString(RestWorker.KEY_URL, url)
                     .build();

        workManager.enqueue(new OneTimeWorkRequest
                            .Builder(RestWorker.class)
                            .setInputData(data)
                            .build());
    }
}
