package com.dawa.mobilehealth.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dawa.mobilehealth.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
    }

    public void LoginOpen(View view) {
        Intent loginopen = new Intent(this, LoginActivity.class);
        startActivity(loginopen);
    }
}
