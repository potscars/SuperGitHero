package com.example.msdhainizam.loginschoolapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by IGWMobileTeam on 04/02/2016.
 */
public class AddChildParser {

    public static String parseAddChild(JSONObject response) {

        int status = 0;
        String message = "NA";

        if(response != null && response.length() > 0) {

            try {

                status = response.getInt("status");

                if(status == 1) {
                    message = response.getString("message");
                } else {
                    message = response.getString("message");
                }

            } catch (JSONException e) {
                message = e.toString();
            }
        }
        return message;
    }
}
