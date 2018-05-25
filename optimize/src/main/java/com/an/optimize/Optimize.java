package com.an.optimize;


import java.util.Map;
import java.util.List;
import android.content.Context;

public class Optimize extends OptimizeModule {

    private static Optimize instance;

    public static Optimize getInstance() {
        if(instance == null) instance = new Optimize();
        return instance;
    }

    public void init(Context context, String url) {
        initialize(context, url);
    }

    public String getStringValue(String param, String defaultValue) {
        return getAbStringValue(param, defaultValue);
    }

    public Number getNumberValue( String param, Number defaultValue) {
        return getAbNumberValue(param, defaultValue);
    }

    public Integer getIntegerValue(String param, Integer defaultValue) {
        return getAbIntegerValue(param, defaultValue);
    }

    public Double getDoubleValue(String param, Double defaultValue) {
        return getAbDoubleValue(param, defaultValue);
    }

    public Boolean getBooleanValue(String param, Boolean defaultValue) {
        return getAbBooleanValue(param, defaultValue);
    }

    public Map getParams(String param, Map defaultValues) {
        return getAbParams(param, defaultValues);
    }

    public List getList(String param, List defaultValues) {
        return getAbList(param, defaultValues);
    }
}
