package com.an.optimizesample;

import android.app.Application;
import com.an.optimize.Optimize;



public class OptimizeApplication extends Application {

    private final String BASE_URL = "https://s3-ap-southeast-1.amazonaws.com/android-optimize/optimize.json";

    @Override
    public void onCreate() {
        super.onCreate();

        Optimize.getInstance().init(getApplicationContext(), BASE_URL);
    }
}
