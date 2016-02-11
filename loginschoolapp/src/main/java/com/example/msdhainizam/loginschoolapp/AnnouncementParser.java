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

    public static final String KEY_DOCUMENTS = "documents";
    public static final String KEY_IMAGEPATH = "path";
    public static final String KEY_IMAGENAME = "name";
    public static final String KEY_FILETYPE = "filetype";

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
                    String imageURL = "Nothing available";

                    for (int i = 0; i < arrayData.length(); i++) {

                        JSONObject announceData = arrayData.getJSONObject(i);
                        JSONObject currentData = announceData.getJSONObject("annoucement");

                        title = currentData.getString("title");
                        content = currentData.getString("text");

                        if(JSONUtils.contains(currentData, KEY_DOCUMENTS)) {
                            JSONArray docArray = currentData.getJSONArray(KEY_DOCUMENTS);

                            for(int j = 0; j < docArray.length(); j++) {

                                imageURL = response.getString("image_url_path");

                                JSONObject docArrayData = docArray.getJSONObject(j);

                                String imagePath = docArrayData.getString(KEY_IMAGEPATH);
                                String imageName = docArrayData.getString(KEY_IMAGENAME);
                                String imageType = docArrayData.getString(KEY_FILETYPE);

                                imageURL += imagePath + imageName + "." + imageType;
                            }
                        }

                        Data data = new Data();
                        data.setTitle(title);
                        data.setContent(content);
                        data.setSchoolName("Sekolah Kebangsaan Jerantut");
                        data.setSchoolImage(imageURL);

                        listAnnounceData.add(data);
                    }
                }else{

                    Data data = new Data();
                    data.setTitle("Error");
                    data.setContent("Error");
                    data.setSchoolName("Error");
                    data.setSchoolImage("Nothing available");
                    listAnnounceData.add(data);
                }
            } catch (JSONException e) {
                Log.d("JSONException ", e.toString());
            }

        }

        return listAnnounceData;
    }
}
