package com.an.optimize;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.an.optimize", appContext.getPackageName());
    }


    @Test
    public void fetchData() throws Exception {

        Context appContext = InstrumentationRegistry.getTargetContext();
        String url = "https://s3-ap-southeast-1.amazonaws.com/android-optimize/optimize.json";

        Optimize.getInstance().init(appContext, url);

        Thread.sleep(10000);

        System.out.println(Optimize.getInstance().getStringValue("string_data", "string_data", "Default Value"));
    }
}
