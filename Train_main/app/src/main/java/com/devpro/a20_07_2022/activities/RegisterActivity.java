package com.devpro.a20_07_2022.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.devpro.a20_07_2022.R;
import com.devpro.a20_07_2022.listeners.BaseResponseListener;
import com.devpro.a20_07_2022.models.user.UserLogin;
import com.devpro.a20_07_2022.models.user.UserResponse;
import com.devpro.a20_07_2022.repository.ServiceImpl;
import com.devpro.a20_07_2022.utils.Constants;
import com.devpro.a20_07_2022.utils.PreferenceManager;

public class RegisterActivity extends AppCompatActivity {

    Button buttonSignUp;
    TextView textSignIn;
    EditText inputEmail, inputPassword, inputConfirmPassword;
    ProgressBar progressBar;

    ServiceImpl service;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonSignUp = findViewById(R.id.buttonSignUp);
        textSignIn = findViewById(R.id.textSignIn);
        inputEmail = findViewById(R.id.inputEmailRegister);
        inputPassword = findViewById(R.id.inputPasswordRegister);
        inputConfirmPassword = findViewById(R.id.inputConfirmPasswordRegister);
        progressBar = findViewById(R.id.progressBarRegister);

        preferenceManager = new PreferenceManager(getApplicationContext());

        service = new ServiceImpl(baseResponseListener);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signUp();
            }
        });

    }

    BaseResponseListener baseResponseListener = new BaseResponseListener() {
        @Override
        public void didFetch(Object response) {
            if (response instanceof UserResponse) {
                if (response != null) {
                    preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    loading(false);
                }
            }
        }

        @Override
        public void didError(int code, String message) {
            Toast.makeText(RegisterActivity.this, "Error " + code + ": " + message, Toast.LENGTH_LONG).show();
            loading(false);
        }
    };

    private void signUp() {
        loading(true);
        if (isValidSignUpDetails()) {
            service.register(new UserLogin(
                    inputEmail.getText().toString().trim(),
                    inputPassword.getText().toString().trim()));
        } else {
            loading(false);
        }
    }

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
        } else if (inputConfirmPassword.getText().toString().trim().isEmpty()) {
            showToast("Confirm your password");
            return false;
        } else if (!inputPassword.getText().toString().trim().equals(inputConfirmPassword.getText().toString().trim())) {
            showToast("Password & confirm password must be same");
            return false;
        } else return true;
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            buttonSignUp.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            buttonSignUp.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}