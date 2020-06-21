package com.dawa.mobilehealth.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.dawa.api.forgotpassword_api;
import com.dawa.mobilehealth.R;
import com.dawa.model.Password;
import com.dawa.url.url;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

     EditText email;
     Button btnsubmit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        email = findViewById(R.id.etfpemail);
        btnsubmit = findViewById(R.id.btnfpsubmit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();

            }
        });
    }

    private void submit() {

        Password passwords = new Password(
                email.getText().toString()

        );

        forgotpassword_api passwordApi = url.getInstance().create(forgotpassword_api.class);
        Call<Void> passwordCall = passwordApi. pass(passwords);
        passwordCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter your email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(ForgotPasswordActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
                email.getText().clear();


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ForgotPasswordActivity.this, "Error:" + t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void LoginOpen(View view) {
        Intent loginopen = new Intent(this, LoginActivity.class);
        startActivity(loginopen);
    }
}
