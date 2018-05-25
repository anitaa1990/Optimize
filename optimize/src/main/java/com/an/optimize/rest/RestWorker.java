package com.an.optimize.rest;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.an.optimize.db.DbExecutorService;
import com.an.optimize.db.OptimizeDbTask;
import com.an.optimize.utils.RequestUtil;
import com.an.optimize.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import androidx.work.Worker;



public class RestWorker extends Worker {


    public static final String KEY_URL = "url";

    @NonNull
    @Override
    public WorkerResult doWork() {

        /*
         * Step 1: check if the url is empty
         * Step 2: get connection with server
         * Step 3: check if the data from backend is valid
         * Step 4: update local db & return success response
         * */

        Object jsonResponse = null;

        try {
            String url = getInputData().getString(KEY_URL, null);
            validateUrl(url);
            jsonResponse = fetchAndValidateData(url);

        } catch (OptimizeException e) {
            e.printStackTrace();

            return WorkerResult.FAILURE;
        }


        Map<String, Object> latestMap = (Map<String, Object>) jsonResponse;
        DbExecutorService.submit(new OptimizeDbTask("attributes", latestMap));


        return WorkerResult.SUCCESS;
    }



    /*
     * Check if the url is empty
     *
     * */
    private void validateUrl(String url) throws OptimizeException {
        if(TextUtils.isEmpty(url)) throw new OptimizeException("Invalid url");
    }



    /*
     * Create a HttpURLConnection connection to backend
     * and fetch data from url.
     * Typically the data is in json format (map)
     *
     * */
    private Object fetchAndValidateData(String url) throws OptimizeException {
        try {
            HttpURLConnection connection = RequestUtil.createGETHttpsURLConnection(url);
            Object jsonResponse = RequestUtil.readHttpResponse(connection);

            return validateData(jsonResponse);

        } catch (IOException e) {
            e.printStackTrace();
            throw new OptimizeException("Exception occurred when connecting to server");
        }
    }



    /*
     * Check if the data from backend is valid
     * If response is null or
     * If the response returned anything other than 200 or
     * If the response is not in Map format
     *      (Check the raw folder to see the typical json format)
     *
     * */
    private Object validateData(Object response) throws OptimizeException {
        if(response == null) {
            throw new OptimizeException("Response data is empty");
        }

        if(response instanceof RequestError) {
            throw new OptimizeException("Response data returned error response");
        }


        Map<String, Object> latestMap = convertStringToMap((String) response);
        if(!(latestMap instanceof Map)) {
            throw new OptimizeException("Response data should be a Map "+ response);
        }

        return latestMap;
    }



    private Map<String, Object> convertStringToMap(String response) throws OptimizeException {
        Map<String, Object> latestMap;

        try {
            latestMap = Util.toMap(response);

        } catch (JSONException e) {
            e.printStackTrace();
            throw new OptimizeException("Response data serialization error "+ response);
        }

        return latestMap;
    }
}
