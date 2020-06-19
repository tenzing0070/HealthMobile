package com.dawa.mobilehealth.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dawa.api.forgotpassword_api;
import com.dawa.api.health_api;

import com.dawa.mobilehealth.R;

import com.dawa.model.Password;
import com.dawa.model.users;
import com.dawa.url.url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etemail;
    private Button btnsubmit;

    String name;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        btnsubmit = findViewById(R.id.btnfpsubmit);
        etemail = findViewById(R.id.etfpemail);

        loaduser();

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
                displayNoti();
            }
        });
    }

    private void displayNoti() {
        Toast.makeText(this,"Sumbitted Successfully", Toast.LENGTH_LONG).show();
        Intent displayNoti = new Intent(this,LoginActivity.class);
        startActivity(displayNoti);
    }

    private void loaduser() {

        health_api healthApi = url.getInstance().create(health_api.class);
        Call<users> passwordCall = healthApi.getUserDetails(url.token);

        passwordCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                name = response.body().getUsername();
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

                Toast.makeText(ForgotPasswordActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void submit() {
        String email = etemail.getText().toString();


        forgotpassword_api forgotpassword_Api = url.getInstance().create(forgotpassword_api.class);

        Call<Password> passwordCall = forgotpassword_Api.submit(url.token,email);

        passwordCall.enqueue(new Callback<Password>() {
            @Override
            public void onResponse(Call<Password> call, Response<Password> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(ForgotPasswordActivity.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<Password> call, Throwable t) {

            }
        });
    }

    public void LoginOpen(View view) {
        Intent loginopen = new Intent(this, LoginActivity.class);
        startActivity(loginopen);
    }
}
