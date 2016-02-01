package com.example.msdhainizam.loginschoolapp;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by IGWMobileTeam on 19/01/2016.
 */
public class VolleySingleTon {

    private static VolleySingleTon mInstance = null;
    private RequestQueue mRequestQueue;

    private VolleySingleTon() {
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    }

    public static  VolleySingleTon getsInstance() {
        if (mInstance == null) {
            mInstance = new VolleySingleTon();
        }

        return mInstance;
    }

    public RequestQueue getmRequestQueue () {
        return mRequestQueue;
    }

}
