package com.dawa.mobilehealth;

import android.os.Bundle;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.adapter.DoctorAdapter;
import com.dawa.api.health_api;
import com.dawa.model.doctors;
import com.dawa.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocAppointmentActivity extends AppCompatActivity {


        DocAppointmentActivity activity;
        private RecyclerView recyclerView;
        //search
        private SearchView searchDoctor;
//        private TextView specialist;
//        private Button btnSearchDoctor;
        DoctorAdapter doctor_Adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_info);
        activity = this;


        recyclerView = findViewById(R.id.doctor_list);
        searchDoctor =(SearchView) findViewById(R.id.search_view_doc);
        loaddoctors();

        searchDoctor.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                doctor_Adapter.getFilter().filter(newText);
                return false;
            }
        });

    }

        private void loaddoctors() {

        health_api doctorAPI = url.getInstance().create(health_api.class);
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
    }

