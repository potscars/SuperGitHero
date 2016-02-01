package com.example.msdhainizam.loginschoolapp;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

/**
 * Created by IGWMobileTeam on 28/01/2016.
 */
public class TaskLoadAnnouncement extends AsyncTask<String, Void, ArrayList<Data>> {

    private AnnounceDataLoadedListener myComponent;
    private VolleySingleTon volleySingleTon;
    private RequestQueue requestQueue;

    public TaskLoadAnnouncement(AnnounceDataLoadedListener myComponent) {

        this.myComponent = myComponent;
        volleySingleTon = VolleySingleTon.getsInstance();
        requestQueue = volleySingleTon.getmRequestQueue();

    }

    @Override
    protected ArrayList<Data> doInBackground(String... params) {

        ArrayList<Data> listAnnounceData = DataUtils.loadAnnouncementData(requestQueue);

        return listAnnounceData;
    }

    @Override
    protected void onPostExecute(ArrayList<Data> listAnnouncement) {

        if(myComponent != null) {
            myComponent.onAnnounceDataLoaded(listAnnouncement);
        }
    }
}
