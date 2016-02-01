package com.example.msdhainizam.loginschoolapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by IGWMobileTeam on 29/01/2016.
 */
public class AnnouncementParser {

    public static ArrayList<Data> parseAnnounceJSON(JSONObject response) {

        ArrayList<Data> listAnnounceData = new ArrayList<>();
        if(response != null && response.length() > 0) {

            try {


                int status = response.getInt("status");

                if(status == 1) {

                    JSONObject announcement = response.getJSONObject("annoucements");
                    JSONArray arrayData = announcement.getJSONArray("data");

                    String title = "Nothing available.";
                    String content = "Nothing available.";

                    for (int i = 0; i < arrayData.length(); i++) {

                        JSONObject announceData = arrayData.getJSONObject(i);
                        JSONObject currentData = announceData.getJSONObject("annoucement");

                        title = currentData.getString("title");
                        content = currentData.getString("text");

                        Data data = new Data();
                        data.setTitle(title);
                        data.setContent(content);
                        data.setSchoolName("Sekolah Kebangsaan Jerantut");
                        data.setSchoolImage(R.drawable.yui_small4);

                        listAnnounceData.add(data);
                    }
                }else{

                    Data data = new Data();
                    data.setTitle("Error");
                    data.setContent("Error");
                    data.setSchoolName("Error");
                    data.setSchoolImage(R.drawable.ic_mail_black_24dp);
                    listAnnounceData.add(data);
                }
            } catch (JSONException e) {
                Log.d("JSONException ", e.toString());
            }

        }

        return listAnnounceData;
    }
}
