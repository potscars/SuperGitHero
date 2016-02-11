package com.example.msdhainizam.loginschoolapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;

import com.android.volley.RequestQueue;

/**
 * Created by IGWMobileTeam on 02/02/2016.
 */
public class TaskLoadProfile extends AsyncTask<String, Void, GuardianData> {

    private ProgressDialog progressDialog;
    private OnProfileDataLoaded myComponent;
    private VolleySingleTon volleySingleTon;
    private RequestQueue requestQueue;

    public TaskLoadProfile(OnProfileDataLoaded myComponent, FragmentActivity activity) {
        this.myComponent = myComponent;
        progressDialog = new ProgressDialog(activity);
        volleySingleTon = VolleySingleTon.getsInstance();
        requestQueue = volleySingleTon.getmRequestQueue();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Loading profile data...");
        progressDialog.show();
    }

    @Override
    protected GuardianData doInBackground(String... params) {

        GuardianData profileData = DataUtils.loadProfileData(requestQueue);
        return profileData;
    }

    @Override
    protected void onPostExecute(GuardianData strings) {
        super.onPostExecute(strings);

        if(myComponent != null) {
            myComponent.onProfileDataLoaded(strings);
        }

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
