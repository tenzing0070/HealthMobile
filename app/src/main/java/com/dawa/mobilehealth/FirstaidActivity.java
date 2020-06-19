package com.dawa.mobilehealth;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.adapter.FirstaidAdapter;
import com.dawa.api.firstaid_api;
import com.dawa.api.health_api;
import com.dawa.model.Instructions;
import com.dawa.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstaidActivity extends AppCompatActivity {

    FirstaidActivity activity;
    private RecyclerView recyclerView;
    private SearchView searchinjury;
    FirstaidAdapter firstaid_Adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_firstaid);
        activity = this;


        recyclerView = findViewById(R.id.firstaid_list);
        searchinjury = findViewById(R.id.search_view);
        loadfirstaids();

        searchinjury.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firstaid_Adapter.getFilter().filter(newText);
                return false;
            }
        });

    }
    private void loadfirstaids() {

        firstaid_api firstaidAPI = url.getInstance().create(firstaid_api.class);
        Call<List<Instructions>> firstaidCall = firstaidAPI.firstaidsDetails(url.token);

        firstaidCall.enqueue(new Callback<List<Instructions>>() {
            @Override
            public void onResponse(Call<List<Instructions>> call, Response<List<Instructions>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(FirstaidActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                List<Instructions> firstaidList = response.body();
                activity.firstaid_Adapter = new FirstaidAdapter(FirstaidActivity.this, firstaidList);
                recyclerView.setAdapter(firstaid_Adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(FirstaidActivity.this,1));

            }

            @Override
            public void onFailure(Call<List<Instructions>> call, Throwable t) {

            }

        });

    }
}
