package com.example.msdhainizam.loginschoolapp;

import com.android.volley.RequestQueue;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by IGWMobileTeam on 28/01/2016.
 */
public class DataUtils {

    public static ArrayList<Data> loadAnnouncementData(RequestQueue requestQueue) {

        JSONObject response = JSONRequestor.requestJSONObject(requestQueue,
                UrlExtras.URL_ANNOUNCEMENT);
        ArrayList<Data> listAnnounceData = AnnouncementParser.parseAnnounceJSON(response);
        MyApplication.getWritableDatabase().insertAnnouncement(listAnnounceData, true);
        return listAnnounceData;
    }

    public static GuardianData loadProfileData(RequestQueue requestQueue) {

        JSONObject response = JSONRequestor.requestJSONObject(requestQueue,
                UrlExtras.URL_GUARDIAN_PROFILE);
        GuardianData profileData = ProfileParser.parseProfileJSON(response);
        return profileData;
    }

    public static String addChild(RequestQueue requestQueue, String icNumber) {

        JSONObject response = JSONRequestor.requestJSONObject(requestQueue,
                UrlExtras.URL_ASSIGN, icNumber);
        String childValidateMessage = AddChildParser.parseAddChild(response);
        return childValidateMessage;
    }
}
