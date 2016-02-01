package com.example.msdhainizam.loginschoolapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by IGWMobileTeam on 11/01/2016.
 */
public class Splash extends Activity {

    private static final int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, InitialStartup.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME);

    }
}
