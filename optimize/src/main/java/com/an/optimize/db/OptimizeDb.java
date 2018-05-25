package com.an.optimize.db;

import com.an.optimize.utils.Util;
import java.io.Serializable;
import java.util.Map;

public class OptimizeDb implements Serializable {


    private static OptimizeDb instance;
    public static OptimizeDb getInstance() {
        if(instance == null) instance = new OptimizeDb();
        return instance;
    }

    private OptimizeDb() {
        if(optimizeDb == null) optimizeDb = (OptimizeDb) Util.readObjectFromDisk(getPackageName());
    }

    private void saveToDisk() {
        Util.writeObjectToDisk(getPackageName(), optimizeDb);
    }


    private String url;
    private String packageName;
    private OptimizeDb optimizeDb;
    private Map<String, Object> attributes;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        saveToDisk();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
        saveToDisk();
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
        saveToDisk();
    }
}
