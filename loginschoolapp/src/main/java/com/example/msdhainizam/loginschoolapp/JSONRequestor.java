package com.example.msdhainizam.loginschoolapp;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by IGWMobileTeam on 29/01/2016.
 */
public class JSONRequestor {

    public static JSONObject requestJSONObject(RequestQueue requestQueue, String url) {
        String token = MyApplication.readTokenFromPreferences();
        Log.d("Token", token);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("tokens", token);

        JSONObject response = null;
        RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url,
                new JSONObject(params),
                requestFuture,
                requestFuture);

        requestQueue.add(request);
        try {
            response = requestFuture.get(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return response;
    }

    public static JSONObject requestJSONObject(RequestQueue requestQueue, String url, String icNumber) {
        String token = MyApplication.readTokenFromPreferences();
        Log.d("Token", token);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("tokens", token);
        params.put("ic_no", icNumber);

        JSONObject response = null;
        RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url,
                new JSONObject(params),
                requestFuture,
                requestFuture);

        requestQueue.add(request);
        try {
            response = requestFuture.get(3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return response;
    }

}
