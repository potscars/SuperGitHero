package com.example.msdhainizam.loginschoolapp;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IGWMobileTeam on 30/01/2016.
 */
public class RegisterRequestor {

    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_PHONENUMBER = "phone";

    public static void requestRegister(RequestQueue requestQueue,
                                       final String email,
                                       final String name,
                                       final String password,
                                       final String phone) {

        Log.d("StringRequest", "Entering...");

        StringRequest regRequest = new StringRequest(Request.Method.POST,
                UrlExtras.URL_SIGNUP,
                createMyReqSuccessListener(),
                createMyReqErrorListener()) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Log.d("MapGetParams", "Entering...");
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_EMAIL, email);
                params.put(KEY_NAME, name);
                params.put(KEY_PASSWORD, password);
                params.put(KEY_PHONENUMBER, phone);

                return params;
            }
        };
        requestQueue.add(regRequest);
    }

    private static Response.Listener<String> createMyReqSuccessListener() {

        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                int status = 0;
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String message = jsonObject.getString("message");
                    status = jsonObject.getInt("status");
                    Toast.makeText(MyApplication.getAppContext(), message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Status", String.valueOf(status));
                directToLoginPage(status);
            }
        };

    }

    private static Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyApplication.getAppContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    public static void directToLoginPage(int status) {

        if(status == 1) {
            Intent intent = new Intent(MyApplication.getAppContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MyApplication.getAppContext().startActivity(intent);
        } else {
            Log.d("Status: ", "Failed to register!");
        }
    }

}
