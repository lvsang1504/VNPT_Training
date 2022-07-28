package com.devpro.a20_07_2022.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.devpro.a20_07_2022.R;
import com.devpro.a20_07_2022.listeners.BaseResponseListener;
import com.devpro.a20_07_2022.listeners.LoginResponseListener;
import com.devpro.a20_07_2022.models.category.CategoryResponse;
import com.devpro.a20_07_2022.models.user.UserLogin;
import com.devpro.a20_07_2022.models.user.UserResponse;
import com.devpro.a20_07_2022.repository.RequestManager;
import com.devpro.a20_07_2022.utils.Constants;
import com.devpro.a20_07_2022.utils.PreferenceManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class LoginActivity extends AppCompatActivity {

    private PreferenceManager preferenceManager;
    Button buttonSignIn;
    TextView textCreateNewAccount, textView, textView2, textForgotPassword;
    EditText inputEmail, inputPassword;
    ProgressBar progressBar;
    Animation anim_from_button, anim_from_top, anim_from_left;
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textForgotPassword = findViewById(R.id.textForgotPassword);
        textCreateNewAccount = findViewById(R.id.textCreateNewAccount);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        requestManager = new RequestManager(baseResponseListener);

        preferenceManager = new PreferenceManager(getApplicationContext());

        if (preferenceManager.getBoolean(Constants.KEY_IS_SIGNED_IN)) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        setListeners();

        //Load Animations
        anim_from_button = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom);
        anim_from_top = AnimationUtils.loadAnimation(this, R.anim.anim_from_top);
        anim_from_left = AnimationUtils.loadAnimation(this, R.anim.anim_from_left);

        textView.setAnimation(anim_from_top);
        textView2.setAnimation(anim_from_top);
        inputEmail.setAnimation(anim_from_left);
        inputPassword.setAnimation(anim_from_left);
        buttonSignIn.setAnimation(anim_from_button);
        textCreateNewAccount.setAnimation(anim_from_button);


        //Hide status bar and navigation bar at the bottom
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        this.getWindow().getDecorView().setSystemUiVisibility(

                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        );
    }

    private void setListeners() {

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidSignUpDetails()) {
                    signIn();
                }
            }
        });

        textCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

    }

    private void signIn() {
        loading(true);
        if (isValidSignUpDetails()) {
            requestManager.getLogin( new UserLogin(inputEmail.getText().toString(), inputPassword.getText().toString()));
        }

    }

    BaseResponseListener baseResponseListener = new BaseResponseListener() {
        @Override
        public void didFetch(Object response) {
            if (response instanceof UserResponse) {
                if (response != null ) {
                    preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    loading(false);
                }
            }
        }

        @Override
        public void didError(int code, String message) {

        }
    };

    private final LoginResponseListener loginResponseListener = new LoginResponseListener() {

        @Override
        public void didFetch(UserResponse userResponse, String message, String cookie) {
            Log.d("TOKEN", userResponse.page + "");
            if (userResponse != null ) {
                preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);

                preferenceManager.putString(Constants.KEY_TOKEN, cookie);

                Log.d("TOKEN", cookie);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            } else {
                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                loading(false);
            }
        }

        @Override
        public void didError(String message) {
            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            loading(false);
        }
    };


    private Boolean isValidSignUpDetails() {
        if (inputEmail.getText().toString().trim().isEmpty()) {
            showToast("Enter email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.getText().toString().trim()).matches()) {
            showToast("Enter valid email");
            return false;
        } else if (inputPassword.getText().toString().trim().isEmpty()) {
            showToast("Enter password");
            return false;
        } else return true;
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            buttonSignIn.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            buttonSignIn.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }



}

