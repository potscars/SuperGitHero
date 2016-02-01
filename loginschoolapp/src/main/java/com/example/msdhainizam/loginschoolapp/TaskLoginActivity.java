package com.example.msdhainizam.loginschoolapp;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;

/**
 * Created by IGWMobileTeam on 22/01/2016.
 */
public class TaskLoginActivity extends AsyncTask<String, Void, String> {

    private String email;
    private String password;
    private String imei;
    private RequestQueue requestQueue;
    private VolleySingleTon volleySingleTon;

    public TaskLoginActivity(String email, String password, String imei) {
        this.email = email;
        this.password = password;
        this.imei = imei;
        volleySingleTon = VolleySingleTon.getsInstance();
        requestQueue = volleySingleTon.getmRequestQueue();
    }

    @Override
    protected String doInBackground(String... params) {

        String app = "Android";

        new LoginProcess(email, password, imei, app, requestQueue).PostLoginVolley();

        return "Executed!";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Log.d("onPostExecute ", s);
    }
}
