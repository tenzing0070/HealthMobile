package com.dawa.mobilehealth.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.adapter.DoctorInfoAdapter;

import com.dawa.api.admin_api;

import com.dawa.mobilehealth.R;
import com.dawa.model.doctors;
import com.dawa.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorInfoActivity extends AppCompatActivity {
    String id;
    DoctorInfoActivity activity;
    private RecyclerView recyclerView;
    private SearchView searchdoctorspecialist;
    DoctorInfoAdapter doctorinfo_Adapter;



    public DoctorInfoActivity() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_doctorinfo);

        activity = this;
        recyclerView = findViewById(R.id.doctors_list);
        searchdoctorspecialist = findViewById(R.id.admin_doctor_search_view);

        loaddoctorinfo();


        searchdoctorspecialist.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                doctorinfo_Adapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    private void loaddoctorinfo() {


        admin_api doctorAPI = url.getInstance().create(admin_api.class);
        Call<List<doctors>> doctorsCall = doctorAPI.getDoctor(url.token);


        doctorsCall.enqueue(new Callback<List<doctors>>() {
            @Override
            public void onResponse(Call<List<doctors>> call, Response<List<doctors>> response) {
                if (response.body().size() == 0) {
                    // Toast.makeText(DoctorInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
                } else {
                    List<doctors> doctorsList = response.body();
                    id=(response.body().get(0).get_id());
                    activity.doctorinfo_Adapter = new DoctorInfoAdapter(DoctorInfoActivity.this, doctorsList);
                    recyclerView.setAdapter(doctorinfo_Adapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(DoctorInfoActivity.this, 1));

                }
            }

            @Override
            public void onFailure(Call<List<doctors>> call, Throwable t) {

            }
        });
    }


    public void OpenAdminDashboard(View view) {
        Intent openadmindashboard = new Intent(this, AdmindashActivity.class);
        startActivity(openadmindashboard);
    }

    public void AdminDashOpen(View view) {
        Intent admindashopen = new Intent(this, DoctorActivity.class);
        startActivity(admindashopen);
    }
}
