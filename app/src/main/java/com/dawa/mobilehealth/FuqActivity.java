package com.dawa.mobilehealth;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.adapter.FaqsAdapter;
import com.dawa.model.Faqs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FuqActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Faqs> faqsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.fragment_faq);

        recyclerView = findViewById(R.id.faq_recycler_view);

        initData();
        setRecyclerView();
    }

    private void setRecyclerView() {
        FaqsAdapter faqsAdapter = new FaqsAdapter(faqsList);
        recyclerView.setAdapter(faqsAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {

        faqsList = new ArrayList();

        faqsList.add(new Faqs("What is health","Health is Wealth"));
        faqsList.add(new Faqs("What is health","Health is Wealth"));
        faqsList.add(new Faqs("What is health","Health is Wealth"));

    }



}
