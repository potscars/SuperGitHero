package com.example.msdhainizam.loginschoolapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by IGWMobileTeam on 19/01/2016.
 */
public class MyApplication extends Application {

    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String PREF_EMAIL = "email";
    public static final String PREF_PASSWORD = "password";
    public static final String PREF_TOKEN = "token";
    public static final String PREF_SAVESTATUS = "savedstatus";
    public static SharedPreferences.Editor prefEditor;
    public static SharedPreferences preferences;

    private static MyApplication mInstance;

    public static MyApplication getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static void saveToSharedPreferences(String token) {

        Log.d("Token", token);
        getAppContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .edit()
                .putString(PREF_TOKEN, token)
                .apply();
    }

    public static boolean readFromPreferences(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return preferences.getBoolean(PREF_SAVESTATUS, false);
    }

    public static String readTokenFromPreferences() {

        preferences = getAppContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String token = preferences.getString(PREF_TOKEN, null);

        return token;
    }

    public static void saveToSharedPreferences(String email, String password, boolean rememberMe) {
        getAppContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .edit()
                .putString(PREF_EMAIL, email)
                .putString(PREF_PASSWORD, password)
                .putBoolean(PREF_SAVESTATUS, rememberMe)
                .apply();
    }

}
