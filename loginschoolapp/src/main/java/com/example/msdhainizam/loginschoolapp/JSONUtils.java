package com.example.msdhainizam.loginschoolapp;

import org.json.JSONObject;

/**
 * Created by IGWMobileTeam on 02/02/2016.
 */
public class JSONUtils {
    public static boolean contains (JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key);
    }
}
