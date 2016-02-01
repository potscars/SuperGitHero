package com.example.msdhainizam.loginschoolapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by IGWMobileTeam on 11/01/2016.
 */
public class InitialStartup extends Activity{

    public static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";
    private static final String PREF_SAVESTATUS = "savedstatus";
    public static SharedPreferences preferences;
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Boolean savedCheck = MyApplication.readFromPreferences(this);

        if (savedCheck) {
            intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            intent = new Intent (this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
