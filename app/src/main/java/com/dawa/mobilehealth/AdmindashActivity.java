package com.dawa.mobilehealth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.dawa.mobilehealth.admin.AppointmentInfoActivity;
import com.dawa.mobilehealth.admin.DoctorActivity;
import com.dawa.mobilehealth.admin.DoctorCrudActivity;
import com.dawa.mobilehealth.admin.FeedbackInfoActivity;
import com.dawa.mobilehealth.admin.UserActivity;

import com.dawa.api.health_api;
import com.dawa.mobilehealth.login.LoginActivity;
import com.dawa.model.users;
import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdmindashActivity extends AppCompatActivity {

    private TextView adminUsername;
    ImageView imgAdminLogout, imgProfileAdmin;
    LinearLayout lldoctor, lluser, llappoitment, llfeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash);

        adminUsername = findViewById(R.id.txtusername);
        imgProfileAdmin = findViewById(R.id.imgProfileAdmin);

        lldoctor = findViewById(R.id.llDoctor);
        lluser = findViewById(R.id.llUser);
        llappoitment = findViewById(R.id.llApointments);
        llfeedback = findViewById(R.id.llFeedback);


        imgAdminLogout = findViewById(R.id.imgAdminLogout);

        openadmininfo();

        imgProfileAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openAdminProfile = new Intent(AdmindashActivity.this, UpdateAdminProfileActivity.class);
                startActivity(openAdminProfile);
            }
        });

        //Admin Logout
        imgAdminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdmindashActivity.this);
                builder.setCancelable(false);
                builder.setMessage("Do you want to Logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        SharedPreferences sharedPreferences = AdmindashActivity.this.getSharedPreferences("Mobile Health", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("token");
                        editor.remove("isadmin");
                        editor.remove("status");
                        editor.remove("username");
                        editor.remove("password");
                        editor.commit();
                        url.token = "Bearer ";
                        url.status = "Status";
                        Intent i = new Intent(AdmindashActivity.this, LoginActivity.class);
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

        //Display Admin`s user all info display CRUD

        lluser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent openAdminUserCRUD = new Intent(AdmindashActivity.this, UserActivity.class);
                startActivity(openAdminUserCRUD);
            }
        });

        //Display Admin`s Doctor all info display CRUD

        lldoctor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent openAdminDoctorCRUD = new Intent(AdmindashActivity.this, DoctorActivity.class);
                startActivity(openAdminDoctorCRUD);
            }
        });

        //Display Doctor Appointment details

        llappoitment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent openAppointmentDetails = new Intent(AdmindashActivity.this, AppointmentInfoActivity.class);
                startActivity(openAppointmentDetails);
            }
        });

        //Display Admin Feedback deails

        llfeedback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent openFeedbackDetails = new Intent(AdmindashActivity.this, FeedbackInfoActivity.class);
                startActivity(openFeedbackDetails);
            }
        });





    }

    private void openadmininfo() {

        health_api hrsApi = url.getInstance().create(health_api.class);
        Call<users> usersCall = hrsApi.getUserDetails(url.token);
        System.out.println("token is:"+url.token);

        usersCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AdmindashActivity.this, "code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if(response.body().getImage()!=null) {
                    String imgpath = url.BASE_URL + response.body().getImage();
                    System.out.println("image response is :"+imgpath);

                    Picasso.get().load(imgpath).into(imgProfileAdmin);

                }
                else
                {
                    Picasso.get().load(R.drawable.image1).into(imgProfileAdmin);
                }

                adminUsername.setText(response.body().getUsername());

            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(AdmindashActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}