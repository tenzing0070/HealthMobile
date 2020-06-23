package com.dawa.mobilehealth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.adapter.FaqsAdapter;
import com.dawa.adapter.FirstaidAdapter;
import com.dawa.api.faq_api;
import com.dawa.api.firstaid_api;
import com.dawa.model.Faqs;
import com.dawa.model.Instructions;
import com.dawa.url.url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaqActivity extends Fragment {

    FaqActivity fragment;
   RecyclerView recyclerView;
    FaqsAdapter faq_Adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_faq, container, false);
        fragment = this;
        recyclerView = view.findViewById(R.id.faq_recycler_view);

        loadfaqs();

        return view;
    }

    private void loadfaqs() {

        faq_api faqAPI = url.getInstance().create(faq_api.class);
        Call<List<Faqs>> faqCall = faqAPI.faqsDetails(url.token);

        faqCall.enqueue(new Callback<List<Faqs>>() {
            @Override
            public void onResponse(Call<List<Faqs>> call, Response<List<Faqs>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getActivity() ,"Error", Toast.LENGTH_SHORT).show();
                }

                List<Faqs> faqList = response.body();
                fragment.faq_Adapter = new FaqsAdapter(getActivity(), faqList);
                recyclerView.setAdapter(faq_Adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));

            }

            @Override
            public void onFailure(Call<List<Faqs>> call, Throwable t) {

            }

        });
    }
}
