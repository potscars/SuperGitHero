package com.example.msdhainizam.loginschoolapp;

import com.android.volley.RequestQueue;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by IGWMobileTeam on 28/01/2016.
 */
public class DataUtils {

    public static ArrayList<Data> loadAnnouncementData(RequestQueue requestQueue) {

        JSONObject response = AnnouncementRequestor.requestAnnouncementJSON(requestQueue);
        ArrayList<Data> listAnnounceData = AnnouncementParser.parseAnnounceJSON(response);
        return listAnnounceData;
    }
}
