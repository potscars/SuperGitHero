package com.example.msdhainizam.loginschoolapp;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

/**
 * Created by IGWMobileTeam on 30/01/2016.
 */
public class TaskRegisterUser extends AsyncTask<String, Void, String> {

    private VolleySingleTon volleySingleTon;
    private RequestQueue requestQueue;
    private String email, name, password, phone;

    public TaskRegisterUser(String email, String name, String password, String phone) {
        this.email =email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        volleySingleTon = VolleySingleTon.getsInstance();
        requestQueue = volleySingleTon.getmRequestQueue();
    }

    @Override
    protected String doInBackground(String... params) {

        RegisterRequestor.requestRegister(requestQueue, email, name, password, phone);
        return null;
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
    }
}
