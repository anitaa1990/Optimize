package com.an.optimize.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Util {


    private static final String LOCALE_CACHE_PATH = "/data/data/%s/genie_exp.dat";

    /* You can use this method to store the
     * request response from your local cache  */
    public static void writeObjectToDisk(String packageName, Object object) {
        String fileName = String.format(LOCALE_CACHE_PATH, packageName);
        DbUtil objDataStream = new DbUtil();
        objDataStream.writeObjects(object,fileName);
    }

    /* You can use this method to retrieve the
     * request response from your local cache  */
    public static Object readObjectFromDisk(String packageName) {
        String fileName = String.format(LOCALE_CACHE_PATH, packageName);
        DbUtil objDataStream = new DbUtil();
        return objDataStream.readObjects(fileName);
    }


    /* You can use this method to convert the
     * request response (jsonObject) to map  */
    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap();
        Iterator keys = object.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            map.put(key, fromJson(object.get(key)));
        }
        return map;
    }


    /* Utility method to parse the request
     * response  */
    private static Object fromJson(Object json) throws JSONException {
        if (json == JSONObject.NULL) {
            return null;
        } else if (json instanceof JSONObject) {
            return (JSONObject) json;
        } else if (json instanceof JSONArray) {
            return toList((JSONArray) json);
        } else {
            return json;
        }
    }


    /* You can use this method to convert the
     * request response (jsonArray) to list  */
    public static List toList(JSONArray array) throws JSONException {
        List list = new ArrayList();
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = (JSONObject) fromJson(array.get(i));
            list.add(jsonObject);
        }
        return list;
    }


    /* You can use this method to convert the
     * request response (jsonObject) to map  */
    public static Map<String, Object> toMap(String string) throws JSONException {
        Map<String, Object> map = new HashMap();
        JSONObject jsonObject = new JSONObject(string);
        Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            map.put(key, fromJson(jsonObject.get(key)));
        }
        return map;
    }
}
