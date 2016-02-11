package com.example.msdhainizam.loginschoolapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.RequestQueue;

/**
 * Created by IGWMobileTeam on 04/02/2016.
 */
public class TaskAssignChild extends AsyncTask<String, Void, String> {

    private ProgressDialog progressDialog;
    private VolleySingleTon volleySingleTon;
    private RequestQueue requestQueue;
    private String icNumber;
    private Activity activity;

    public TaskAssignChild(Activity activity, String icNumber) {

        this.activity = activity;
        progressDialog = new ProgressDialog(activity);
        this.icNumber = icNumber;
        volleySingleTon = VolleySingleTon.getsInstance();
        requestQueue = volleySingleTon.getmRequestQueue();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Adding child...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        String childValidateMessage = DataUtils.addChild(requestQueue, icNumber);
        return childValidateMessage;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        Toast.makeText(activity, s, Toast.LENGTH_LONG).show();
    }
}
