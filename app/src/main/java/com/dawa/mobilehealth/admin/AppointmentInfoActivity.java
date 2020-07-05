package com.dawa.mobilehealth.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.adapter.AppointmentInfoAdapter;

import com.dawa.api.admin_api;
import com.dawa.mobilehealth.R;
import com.dawa.model.Booking;

import com.dawa.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentInfoActivity extends AppCompatActivity {

    AppointmentInfoActivity activity;
    private RecyclerView recyclerView;

    AppointmentInfoAdapter appointmentinfo_Adapter;

    public AppointmentInfoActivity() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_appointmentinfo);

        activity = this;
        recyclerView = findViewById(R.id.appointment_detail_list);


        loadappointmentinfo();



    }
    private void loadappointmentinfo() {


        admin_api appointAPI = url.getInstance().create(admin_api.class);
        Call<List<Booking>> appointsCall = appointAPI.getAppointment(url.token);
        System.out.println("token is:"+url.token);


        appointsCall.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(AppointmentInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                List<Booking> appointmentsList = response.body();
                activity.appointmentinfo_Adapter = new AppointmentInfoAdapter(AppointmentInfoActivity.this, appointmentsList);
                recyclerView.setAdapter(appointmentinfo_Adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(AppointmentInfoActivity.this,1));

            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {

            }
        });
    }


    public void AdminDashOpen(View view) {
        Intent admindashopen = new Intent(this, AdmindashActivity.class);
        startActivity(admindashopen);
    }
}
