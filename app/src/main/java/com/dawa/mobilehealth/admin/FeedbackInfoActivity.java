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

import com.dawa.adapter.FeedbackInfoAdapter;
import com.dawa.api.admin_api;
import com.dawa.mobilehealth.R;

import com.dawa.model.feedbacks;
import com.dawa.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackInfoActivity extends AppCompatActivity {

    FeedbackInfoActivity activity;
    private RecyclerView recyclerView;

    private SearchView searchfeedbackemail;

    FeedbackInfoAdapter feedbackinfo_Adapter;

    public FeedbackInfoActivity() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_feedbackinfo);

        activity = this;
        recyclerView = findViewById(R.id.feedbackslist);

        searchfeedbackemail = findViewById(R.id.admin_feedback_search_view);

        loadfeedbackinfo();

        searchfeedbackemail.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                feedbackinfo_Adapter.getFilter().filter(newText);
                return false;
            }
        });



    }
    private void loadfeedbackinfo() {


        admin_api feedbackAPI = url.getInstance().create(admin_api.class);
        Call<List<feedbacks>> feedsCall = feedbackAPI.getUserFeedback(url.token);
        System.out.println("token is:"+url.token);


        feedsCall.enqueue(new Callback<List<feedbacks>>() {
            @Override
            public void onResponse(Call<List<feedbacks>> call, Response<List<feedbacks>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(FeedbackInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                List<feedbacks> feedbacksList = response.body();
                activity.feedbackinfo_Adapter = new FeedbackInfoAdapter(FeedbackInfoActivity.this, feedbacksList);
                recyclerView.setAdapter(feedbackinfo_Adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(FeedbackInfoActivity.this,1));

            }

            @Override
            public void onFailure(Call<List<feedbacks>> call, Throwable t) {

            }
        });
    }

    public void AdminDashOpen(View view) {
        Intent admindashopen = new Intent(this, AdmindashActivity.class);
        startActivity(admindashopen);
    }
}
