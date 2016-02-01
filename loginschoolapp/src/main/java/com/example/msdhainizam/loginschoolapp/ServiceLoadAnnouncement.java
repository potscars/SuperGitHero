package com.example.msdhainizam.loginschoolapp;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

import java.util.ArrayList;

/**
 * Created by IGWMobileTeam on 30/01/2016.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class ServiceLoadAnnouncement extends JobService implements AnnounceDataLoadedListener {
    @Override
    public void onAnnounceDataLoaded(ArrayList<Data> listAnnounceData) {

    }

    @Override
    public boolean onStartJob(JobParameters params) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
