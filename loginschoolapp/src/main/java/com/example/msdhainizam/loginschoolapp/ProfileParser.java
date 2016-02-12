package com.example.msdhainizam.loginschoolapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by IGWMobileTeam on 02/02/2016.
 */
public class ProfileParser {

    public static final String NA ="NA";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_GUARDIAN = "guardian";
    public static final String KEY_STUDENT = "students";

    public static GuardianData parseProfileJSON(JSONObject response) {

        GuardianData profileData = new GuardianData();
        if(response != null && response.length() >0){

            try {

                int status = response.getInt("status");

                String name = NA;
                String email = NA;
                String phoneNumber = NA;
                String childrenName = NA;
                String childrenIC = NA;
                String childrenSchool = NA;
                String childForArray = NA;
                ArrayList<String> childName = new ArrayList<>();
                ArrayList<String> childIC = new ArrayList<>();

                if (status == 1) {

                    JSONObject profileObject = response.getJSONObject("user");

                    if (JSONUtils.contains(profileObject, KEY_NAME)) {
                        name = profileObject.getString(KEY_NAME);
                    }

                    if(JSONUtils.contains(profileObject, KEY_EMAIL)) {
                        email = profileObject.getString(KEY_EMAIL);
                    }

                    if(JSONUtils.contains(profileObject, KEY_PHONE)) {
                        phoneNumber = profileObject.getString(KEY_PHONE);
                    }

                    if (JSONUtils.contains(profileObject, KEY_GUARDIAN)) {
                        JSONObject guardian = profileObject.getJSONObject(KEY_GUARDIAN);
                        if (JSONUtils.contains(guardian, KEY_STUDENT)) {
                            JSONArray student = guardian.getJSONArray(KEY_STUDENT);

                            for (int i=0; i < student.length(); i++) {
                                JSONObject jsonObject = student.getJSONObject(i);

                                //childData = new ArrayList<>();

                                childrenName = jsonObject.getString("name");
                                childrenIC = jsonObject.getString("ic_no");
                                childForArray = "\n" + childrenName +
                                "\n" + childrenIC;

                                Log.d("Child data", childrenName + " " + childrenIC);
                                childName.add(childrenName);
                                childIC.add(childrenIC);
                            }
                        }
                    }

                    Log.d("Profileparser", name +" "+ email +" "+ phoneNumber);

                    profileData.setName(name);
                    profileData.setEmail(email);
                    profileData.setPhoneNumber(phoneNumber);
                    profileData.setChildName(childName);
                    profileData.setChildICNumber(childIC);

                }else {
                    profileData.setName(name);
                    profileData.setEmail(email);
                    profileData.setPhoneNumber(phoneNumber);
                }

            }catch (JSONException e) {
                Log.d("JSONException ", e.toString());
            }
        }

        return profileData;
    }
}
