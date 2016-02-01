package com.example.msdhainizam.loginschoolapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mehdi.sakout.fancybuttons.FancyButton;


/**
 * Created by IGWMobileTeam on 19/01/2016.
 */
public class RegisterActivity extends Activity {

    private EditText inputName, inputEmail, inputPassword, inputPhone, inputPasswordConfirm;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword, inputLayoutPasswordConfirm, inputLayoutPhone;
    private FancyButton btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputLayoutPasswordConfirm = (TextInputLayout) findViewById(R.id.input_layout_passwordConfirm);
        inputLayoutPhone = (TextInputLayout) findViewById(R.id.input_layout_phone);
        inputName = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        inputPasswordConfirm = (EditText) findViewById(R.id.input_passwordconfirm);
        inputPhone = (EditText) findViewById(R.id.input_phone);
        btnSignUp = (FancyButton) findViewById(R.id.btn_signup);
        ImageView imageview = (ImageView) findViewById(R.id.imageView);
        Glide.with(this).load(R.drawable.yui_small2).into(imageview);

        ImageView imageView2 = (ImageView) findViewById(R.id.backdrop);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.school);
        Bitmap blurredBitmap = blur(bitmap);
        imageView2.setImageBitmap(blurredBitmap);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Registration");

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));
        inputPasswordConfirm.addTextChangedListener(new MyTextWatcher(inputPasswordConfirm));
        inputPhone.addTextChangedListener(new MyTextWatcher(inputPhone));

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForm();
                submitRegister();
            }
        });
    }

    private static final float BLUR_RADIUS = 25f;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Bitmap blur(Bitmap image) {
        if (null == image) return null;

        Bitmap outputBitmap = Bitmap.createBitmap(image);
        final RenderScript renderScript = RenderScript.create(this);
        Allocation tmpIn = Allocation.createFromBitmap(renderScript, image);
        Allocation tmpOut = Allocation.createFromBitmap(renderScript, outputBitmap);

        //Intrinsic Gausian blur filter
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        theIntrinsic.setRadius(BLUR_RADIUS);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }

    private void submitRegister() {
        final String email = inputEmail.getText().toString();
        final String name = inputName.getText().toString();
        final String password = inputPassword.getText().toString();
        final String phoneNumber = inputPhone.getText().toString();

        new TaskRegisterUser(email, name, password,phoneNumber).execute();
    }

    private void checkForm() {
        Log.d("ValidatePass", String.format("ValidateName: %s ", validateName()));
        if (!validateName()) {
            return;
        }
        Log.d("ValidatePass", String.format("ValidateEmail: %s ", validateEmail()));
        if (!validateEmail()) {
            return;
        }
        Log.d("ValidatePass", String.format("ValidatePassword: %s ", validatePassword()));
        if (!validatePassword()) {
            return;
        }
        Log.d("ValidatePass", String.format("ValidatePasswordConfirm: %s ", validatePasswordConfirm()));
        if (!validatePasswordConfirm()) {
            return;
        }
        Log.d("ValidatePass", String.format("ValidatePhone: %s ", validatePhone()));
        if (!validatePhone()) {
            return;
        }
        Log.d("ValidatePass", String.format("ValidatePasswordConfirmSame: %s ", validatePasswordConfirmSame()));
        if (!validatePasswordConfirmSame()) {
            return;
        }
        //Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError("Enter your full name");
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError("Enter valid email address");
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }
        return true;

    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError("enter the password");
            requestFocus(inputPassword);
            return false;

        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePasswordConfirm() {
        if (inputPasswordConfirm.getText().toString().trim().isEmpty()) {
            inputLayoutPasswordConfirm.setError("retype the password");
            requestFocus(inputPasswordConfirm);
            return false;

        } else {
            inputLayoutPasswordConfirm.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePasswordConfirmSame() {
        Log.d("ValidatePass", String.format("Pass: %s, confirmpass: %s ", inputPassword.getText(), inputPasswordConfirm.getText()));
        if (!inputPassword.getText().toString().equals(inputPasswordConfirm.getText().toString())) {
            inputLayoutPasswordConfirm.setError("password did not match");
            //requestFocus(inputPasswordConfirm);
            return false;

        } else {
            inputLayoutPasswordConfirm.setErrorEnabled(false);

        }
        return true;

    }

    private boolean validatePhone() {
        if (inputPhone.getText().toString().trim().isEmpty()) {
            inputLayoutPhone.setError("Enter your phone number");
            requestFocus(inputPhone);
            return false;
        } else {
            inputLayoutPhone.setErrorEnabled(false);
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public class MyTextWatcher implements TextWatcher {
        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
                case R.id.input_passwordconfirm:
                    validatePasswordConfirm();
                    break;
                case R.id.input_phone:
                    validatePhone();
                    break;

            }
        }
    }
}
