package com.poll.mobilehealthwearable;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.poll.Url.url;
import com.poll.api.health_api;
import com.poll.model.users;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends Activity {

    ImageView imgHealthrecord, imgAdminLogout, imgStopwatch, imgFootstep;
    private TextView firstname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        firstname = findViewById(R.id.txtfirstname);
        imgAdminLogout = findViewById(R.id.imgAdminLogout);
        imgHealthrecord = findViewById(R.id.imgHealthrecord);
        imgStopwatch = findViewById(R.id.imgStopwatch);
        imgFootstep = findViewById(R.id.imgFootstep);


        openuserinfo();
        imgHealthrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openqrscan = new Intent(Dashboard.this, UpdateHealthRecordActivity.class);
                startActivity(openqrscan);
            }
        });

        imgStopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openStopwatch = new Intent(Dashboard.this, StopwatchActivity.class);
                startActivity(openStopwatch);
            }
        });

        imgFootstep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent opneFootstep = new Intent(Dashboard.this, FootStepsActivity.class);
                startActivity(opneFootstep);
            }
        });




        imgAdminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
                builder.setCancelable(false);
                builder.setMessage("Do you want to Logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        url.token = "Bearer ";
                        Intent i = new Intent(Dashboard.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });


    }

    private void openuserinfo() {

        health_api hrsApi = url.getInstance().create(health_api.class);
        Call<users> usersCall = hrsApi.getUserDetails(url.token);
        System.out.println("token is:"+url.token);

        usersCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Dashboard.this, "code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                firstname.setText(response.body().getFirstname());
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(Dashboard.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }






}
