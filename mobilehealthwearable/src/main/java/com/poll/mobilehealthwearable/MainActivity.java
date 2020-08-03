package com.poll.mobilehealthwearable;


import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.poll.Url.url;
import com.poll.api.health_api;
import com.poll.model.users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends WearableActivity  {

    Button btnLogin;
    EditText etusername, etpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        etusername = findViewById(R.id.username);
        etpassword = findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });

    }



    private void login() {

        final String username = etusername.getText().toString();
        final String password = etpassword.getText().toString();




        if (!username.isEmpty() && !password.isEmpty()) {
            users userLogin = new users(username, password);
            health_api loginApi = url.getInstance().create(health_api.class);
            Call<users> loginCall = loginApi.login(userLogin);

            loginCall.enqueue(new Callback<users>() {
                @Override
                public void onResponse(Call<users> call, Response<users> response) {
                    System.out.println("The response is : " + response);
                    if (!response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Username or Password do not matched", Toast.LENGTH_SHORT).show();
                        return;
                    } else {

                        url.token += response.body().getToken();
                        openDashboard();
                    }
                }

                @Override
                public void onFailure(Call<users> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(MainActivity.this, "Enter Field", Toast.LENGTH_SHORT).show();

        }
    }



    public void openDashboard(){
        Intent openDash = new Intent(this, Dashboard.class);
        startActivity(openDash);
    }


}