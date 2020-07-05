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

import com.dawa.adapter.FaqInfoAdapter;

import com.dawa.api.admin_api;
import com.dawa.mobilehealth.R;
import com.dawa.model.Faqs;

import com.dawa.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaqInfoActivity extends AppCompatActivity {

    FaqInfoActivity activity;
    private RecyclerView recyclerView;
    private SearchView searchinfo;
    FaqInfoAdapter faqinfo_Adapter;

    public FaqInfoActivity() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_faqinfo);

        activity = this;
        recyclerView = findViewById(R.id.faqs_list);
        searchinfo = findViewById(R.id.admin_faq_search_view);

        loadfaqinfo();

        searchinfo.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                faqinfo_Adapter.getFilter().filter(newText);
                return false;
            }
        });

    }
    private void loadfaqinfo() {


        admin_api faqAPI = url.getInstance().create(admin_api.class);
        Call<List<Faqs>> faqsCall = faqAPI.getAdminFaqDetails(url.token);
        System.out.println("token is:"+url.token);

        faqsCall.enqueue(new Callback<List<Faqs>>() {
            @Override
            public void onResponse(Call<List<Faqs>> call, Response<List<Faqs>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(FaqInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                List<Faqs> faqsList = response.body();
                activity.faqinfo_Adapter = new FaqInfoAdapter(FaqInfoActivity.this, faqsList);
                recyclerView.setAdapter(faqinfo_Adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(FaqInfoActivity.this,1));

            }

            @Override
            public void onFailure(Call<List<Faqs>> call, Throwable t) {

            }

        });
    }

    public void OpenAdminDashboard(View view) {
        Intent openadmindashboard = new Intent(this, AdmindashActivity.class);
        startActivity(openadmindashboard);
    }

    public void AdminDashOpen(View view) {
        Intent admindashopen = new Intent(this, FaqActivity.class);
        startActivity(admindashopen);
    }
}

