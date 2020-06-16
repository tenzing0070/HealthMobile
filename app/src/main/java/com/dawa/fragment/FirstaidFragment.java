package com.dawa.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.adapter.FirstaidAdapter;
import com.dawa.api.health_api;
import com.dawa.mobilehealth.FootStepsActivity;
import com.dawa.mobilehealth.R;
import com.dawa.model.Instructions;
import com.dawa.url.url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstaidFragment extends Fragment {

    public FirstaidFragment() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_firstaid, container, false);

        return v;
    }
}

//public class FirstaidFragment extends AppCompatActivity {
//
//    FirstaidFragment activity;
//    private RecyclerView recyclerView;
//    private SearchView searchinjury;
//    private Button btnSearch;
//    FirstaidAdapter firstaid_Adapter;
//
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_firstaid);
//        activity = this;
//
//
//        recyclerView = findViewById(R.id.firstaid_list);
//        searchinjury = findViewById(R.id.search_view);
//        loadfirstaids();
//
//        searchinjury.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                firstaid_Adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//
//    }
//        private void loadfirstaids() {
//
//            health_api firstaidAPI = url.getInstance().create(health_api.class);
//            Call<List<Instructions>> firstaidCall = firstaidAPI.firstaidsDetails(url.token);
//
//            firstaidCall.enqueue(new Callback<List<Instructions>>() {
//                @Override
//                public void onResponse(Call<List<Instructions>> call, Response<List<Instructions>> response) {
//                    if(!response.isSuccessful()) {
//                        Toast.makeText(FirstaidFragment.this, "Error", Toast.LENGTH_SHORT).show();
//                    }
//
//                    List<Instructions> firstaidList = response.body();
//                    activity.firstaid_Adapter = new FirstaidAdapter(FirstaidFragment.this, firstaidList);
//                    recyclerView.setAdapter(firstaid_Adapter);
//                    recyclerView.setLayoutManager(new GridLayoutManager(FirstaidFragment.this,1));
//
//                }
//
//                @Override
//                public void onFailure(Call<List<Instructions>> call, Throwable t) {
//
//                }
//
//            });
//
//        }
//    }





