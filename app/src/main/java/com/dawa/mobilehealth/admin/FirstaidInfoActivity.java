package com.dawa.mobilehealth.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.adapter.FirstaidInfoAdapter;

import com.dawa.api.admin_api;
import com.dawa.mobilehealth.R;
import com.dawa.model.Instructions;

import com.dawa.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstaidInfoActivity extends AppCompatActivity {


        FirstaidInfoActivity activity;
        private RecyclerView recyclerView;
        private SearchView searchcodename;
        FirstaidInfoAdapter firstaidinfo_Adapter;

    public FirstaidInfoActivity() {

    }
        @SuppressLint("SetJavaScriptEnabled")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_firstaidinfo);

        activity = this;
        recyclerView = findViewById(R.id.firstaids_list);
        searchcodename = findViewById(R.id.admin_firstaid_search_view);

            loadfirstaidinfo();

            searchcodename.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firstaidinfo_Adapter.getFilter().filter(newText);
                return false;
            }
        });

    }
        private void loadfirstaidinfo() {


        admin_api firstaidAPI = url.getInstance().create(admin_api.class);
        Call<List<Instructions>> usersCall = firstaidAPI.getAdminFirstaidDetails(url.token);
        System.out.println("token is:"+url.token);


        usersCall.enqueue(new Callback<List<Instructions>>() {
            @Override
            public void onResponse(Call<List<Instructions>> call, Response<List<Instructions>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(FirstaidInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                List<Instructions> InstructionsList = response.body();
                activity.firstaidinfo_Adapter = new FirstaidInfoAdapter(FirstaidInfoActivity.this, InstructionsList);
                recyclerView.setAdapter(firstaidinfo_Adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(FirstaidInfoActivity.this,1));

            }

            @Override
            public void onFailure(Call<List<Instructions>> call, Throwable t) {

            }
        });
    }

        public void OpenAdminDashboard(View view) {
        Intent openadmindashboard = new Intent(this, AdmindashActivity.class);
        startActivity(openadmindashboard);
    }

        public void AdminDashOpen(View view) {
        Intent admindashopen = new Intent(this, FirstaidActivity.class);
        startActivity(admindashopen);
    }
    }

