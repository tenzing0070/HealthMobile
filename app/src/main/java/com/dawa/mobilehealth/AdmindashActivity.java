package com.dawa.mobilehealth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dawa.api.health_api;
import com.dawa.mobilehealth.login.LoginActivity;
import com.dawa.model.users;
import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdmindashActivity extends AppCompatActivity {

    private TextView firstname;
    ImageView imgAdminLogout, imgProfileAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash);

        firstname = findViewById(R.id.txtfirstname);
        imgProfileAdmin = findViewById(R.id.imgProfileAdmin);

        imgAdminLogout = findViewById(R.id.imgAdminLogout);

        openadmininfo();

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
                        SharedPreferences sharedPreferences = AdmindashActivity.this.getSharedPreferences("Ride", MODE_PRIVATE);
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

                firstname.setText(response.body().getFirstname());

            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(AdmindashActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}