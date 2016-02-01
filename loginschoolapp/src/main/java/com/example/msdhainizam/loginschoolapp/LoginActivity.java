package com.example.msdhainizam.loginschoolapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import mehdi.sakout.fancybuttons.FancyButton;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private FancyButton mLoginButton;
    private TextView mTVRegister;
    private ImageView mImageView;
    private String emailInput;
    private String passwordInput;
    private CheckBox rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.imageView);
        mLoginButton = (FancyButton) findViewById(R.id.btn_login);
        mTVRegister = (TextView)findViewById(R.id.register);
        mEmail = (EditText) findViewById(R.id.emailLogin);
        mPassword = (EditText) findViewById(R.id.passwordLogin);
        rememberMe = (CheckBox) findViewById(R.id.checkRememberMe);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireLogin();
            }
        });
        mTVRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireRegister();
            }
        });

        Glide.with(this).load(R.drawable.yui_small2).into(mImageView);
    }

    public void fireLogin() {

        emailInput = mEmail.getText().toString();
        passwordInput = mPassword.getText().toString();

        if(rememberMe.isChecked()) {
            MyApplication.saveToSharedPreferences(emailInput, passwordInput, true);
        }

        new TaskLoginActivity(emailInput, passwordInput, getIMEI()).execute();
    }

    public void fireRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String imeiID = telephonyManager.getDeviceId();

        return imeiID;
    }

}