package com.an.optimize.utils;

import com.an.optimize.rest.RequestError;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class RequestUtil {

    /* set the timeout for a connection  */
    private final static int ONE_MINUTE = 60000;

    public enum REQUESTTYPE {
        GET, POST, PUT, DELETE
    }


    private static String getHttpResponse(InputStream inputStream) throws IOException {
        StringBuffer sb = new StringBuffer();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    public static Object readHttpResponse(HttpURLConnection connection) {
        try {
            Integer responseCode = connection.getResponseCode();
            InputStream inputStream;

            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                String responseString = getHttpResponse(inputStream);
                return responseString;

            } else {
                inputStream = connection.getErrorStream();
                String errorString = getHttpResponse(inputStream);

                /* For ease of use, we are creating an separate
                 * requestError object class and adding the
                 * responseCode & the response message to this class
                 * This can be commented out if not needed and you
                 * can pass only the error String if you wish */
                RequestError requestError = new RequestError();
                requestError.setResponseMessage(errorString);
                requestError.setResponseCode(responseCode);
                return requestError;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }


    public static HttpURLConnection createGETHttpsURLConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(REQUESTTYPE.GET.name());
        return connection;
    }

    public static HttpURLConnection createDELETEHttpsURLConnection(String urlString)
            throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(REQUESTTYPE.DELETE.name());
        return connection;
    }


    public static HttpURLConnection createPOSTHttpsURLConnection(String urlString, JSONObject jsonObject)
            throws IOException {
        HttpURLConnection connection = createHttpsURLConnection(urlString);
        connection.setRequestMethod(REQUESTTYPE.POST.name());
        connection = defineHttpsURLConnection(connection, jsonObject);
        return connection;
    }


    public static HttpURLConnection createPUTHttpsURLConnection(String urlString, JSONObject jsonObject)
            throws IOException {
        HttpURLConnection connection = createHttpsURLConnection(urlString);
        connection.setRequestMethod(REQUESTTYPE.PUT.name());
        connection = defineHttpsURLConnection(connection, jsonObject);
        return connection;
    }


    private static HttpURLConnection defineHttpsURLConnection(HttpURLConnection connection, JSONObject jsonObject) {
        try {
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            /* Add headers to the request */
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.write(jsonObject.toString().getBytes(Charset.forName("UTF-8")));
            wr.flush();
            wr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


    private static HttpURLConnection createHttpsURLConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setConnectTimeout(ONE_MINUTE);
        connection.setReadTimeout(ONE_MINUTE);

        return connection;
    }
}
