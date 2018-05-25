package com.an.optimize.db;

import java.util.Map;

public class OptimizeDbModule {


    private static OptimizeDbModule ourInstance;
    public synchronized static OptimizeDbModule getInstance() {
        if(ourInstance == null) ourInstance = new OptimizeDbModule();
        return ourInstance;
    }

    private OptimizeDb optimizeDb;
    private OptimizeDbModule() {
        optimizeDb = OptimizeDb.getInstance();
    }


    public String getUrl() {
        return optimizeDb.getUrl();
    }


    private void addAttributes(Map<String, Object> attributes) {
        Map<String, Object> localAttributesMap = optimizeDb.getAttributes();

        /*
         *   We are using checksum as a way to check if the local data & the backend data are in sync:
         *   Update local db if
         *   a. the local db is empty
         *   b. the local db does not contain the checksum value
         *   c. the backend data does not contain the checksum value
         *   d. the checksum in local does not match the checksum in the backend data
         *
         */

        if(localAttributesMap == null
                || !localAttributesMap.containsKey("checksum")
                || !attributes.containsKey("checksum")
                || !String.valueOf(localAttributesMap.get("checksum")).equalsIgnoreCase(String.valueOf(attributes.get("checksum")))) {

            optimizeDb.setAttributes(attributes);
        }
    }

    public Map<String, Object> getAttributes() {
        return optimizeDb.getAttributes();
    }


    public void write(String key, Object value) {
        if(key.equalsIgnoreCase("url")) optimizeDb.setUrl((String) value);
        else if(key.equalsIgnoreCase("packageName")) optimizeDb.setPackageName((String) value);
        else if(key.equalsIgnoreCase("attributes")) addAttributes((Map<String, Object>) value);
    }
}
