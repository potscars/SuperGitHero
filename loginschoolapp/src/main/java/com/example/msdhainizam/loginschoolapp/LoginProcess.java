package com.example.msdhainizam.loginschoolapp;

import android.content.Context;
import android.content.Intent;
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
 * Created by IGWMobileTeam on 26/01/2016.
 */
public class LoginProcess {

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_IMEI = "imei";
    public static final String KEY_APP = "app";

    private Context context;
    private String mEmail;
    private String mPassword;
    private String mIMEI;
    private String mApp;
    private RequestQueue requestQueue;
    public static String URL_LOGIN = "http://schoolapp.myapp.my/api/user/login";

    public LoginProcess(String email, String password, String imei, String app, RequestQueue requestQueue) {
        mEmail = email;
        mPassword = password;
        mIMEI = imei;
        mApp = app;
        this.requestQueue = requestQueue;
        context = MyApplication.getAppContext();
    }

    public void PostLoginVolley() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_LOGIN,
                createMyReqSuccessListener(),
                createMyReqErrorListener()) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put(KEY_EMAIL, mEmail);
                params.put(KEY_PASSWORD, mPassword);
                params.put(KEY_IMEI, mIMEI);
                params.put(KEY_APP, mApp);

                return params;
            }
        };

        requestQueue.add(stringRequest);

    }

    public void directToHome(String token) {

        MyApplication.saveToSharedPreferences(token);

        Intent intent = new Intent();
        intent.setClass(MyApplication.getAppContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private Response.Listener<String> createMyReqSuccessListener() {

        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String message = "";

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    int status = jsonObject.getInt("status");

                    if(status==1) {
                        message = jsonObject.getString("message");
                        String token = jsonObject.getString("tokens");
                        directToHome(token);
                    }else if(status == 0) {
                        message = jsonObject.getString("message");
                    }

                    Toast.makeText(MyApplication.getAppContext(), message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //assert jsonResponse != null;
                //Toast.makeText(MyApplication.getAppContext(),response, Toast.LENGTH_SHORT).show();
            }
        };
    }


    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyApplication.getAppContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }

}
