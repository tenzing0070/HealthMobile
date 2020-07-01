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

import com.dawa.adapter.UserInfoAdapter;
import com.dawa.api.admin_api;

import com.dawa.mobilehealth.AdmindashActivity;
import com.dawa.mobilehealth.R;

import com.dawa.model.users;
import com.dawa.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoActivity extends AppCompatActivity {

    UserInfoActivity activity;
    private RecyclerView recyclerView;
    private SearchView searchusername;
    UserInfoAdapter userinfo_Adapter;

    public UserInfoActivity() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_userinfo);

        activity = this;
        recyclerView = findViewById(R.id.users_list);
        searchusername = findViewById(R.id.admin_user_search_view);

        loaduserinfo();

        searchusername.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userinfo_Adapter.getFilter().filter(newText);
                return false;
            }
        });

    }
    private void loaduserinfo() {


        admin_api userAPI = url.getInstance().create(admin_api.class);
        Call<List<users>> usersCall = userAPI.getCustomer(url.token);
        System.out.println("token is:"+url.token);


        usersCall.enqueue(new Callback<List<users>>() {
            @Override
            public void onResponse(Call<List<users>> call, Response<List<users>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(UserInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                List<users> usersList = response.body();
                activity.userinfo_Adapter = new UserInfoAdapter(UserInfoActivity.this, usersList);
                recyclerView.setAdapter(userinfo_Adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(UserInfoActivity.this,1));

            }

            @Override
            public void onFailure(Call<List<users>> call, Throwable t) {

            }
        });
    }

    public void OpenAdminDashboard(View view) {
        Intent openadmindashboard = new Intent(this, AdmindashActivity.class);
        startActivity(openadmindashboard);
    }

    public void AdminDashOpen(View view) {
        Intent admindashopen = new Intent(this, UserActivity.class);
        startActivity(admindashopen);
    }
}
