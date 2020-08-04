package com.poll.mobilehealthwearable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poll.Url.url;
import com.poll.adapter.DoctorAdapter;
import com.poll.api.doctor_api;
import com.poll.model.doctors;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DocAppointmentActivity extends Activity {


        DocAppointmentActivity activity;
        private RecyclerView recyclerView;


        DoctorAdapter doctor_Adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_info);
        activity = this;


        recyclerView = findViewById(R.id.doctor_list);

        loaddoctors();



    }

        private void loaddoctors() {

        doctor_api doctorAPI = url.getInstance().create(doctor_api.class);
        Call<List<doctors>> doctorCall = doctorAPI.doctorsDetails(url.token);


        doctorCall.enqueue(new Callback<List<doctors>>() {
            @Override
            public void onResponse(Call<List<doctors>> call, Response<List<doctors>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(DocAppointmentActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                List<doctors> doctorsList = response.body();
                activity.doctor_Adapter = new DoctorAdapter(DocAppointmentActivity.this, doctorsList);
                recyclerView.setAdapter(doctor_Adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(DocAppointmentActivity.this,2));

            }

            @Override
            public void onFailure(Call<List<doctors>> call, Throwable t) {

            }
        });
    }

    public void UserDashOpen(View view) {
        Intent openUserDash = new Intent(this, Dashboard.class);
        startActivity(openUserDash);
    }
    }

