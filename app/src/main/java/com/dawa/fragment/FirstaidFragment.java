package com.dawa.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.adapter.FirstaidAdapter;
import com.dawa.mobilehealth.FootStepsActivity;
import com.dawa.mobilehealth.R;
import com.dawa.model.Instructions;

import java.util.ArrayList;
import java.util.List;

public class FirstaidFragment extends Fragment {

    RecyclerView recyclerView;
    List<Instructions> instructionsList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_firstaid, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewFirstAid);

        initData();
        setRecyclerView();
        return view;
    }

    private void setRecyclerView() {
        FirstaidAdapter firstaidAdapter = new FirstaidAdapter(instructionsList);
        recyclerView.setAdapter(firstaidAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initData() {

        instructionsList = new ArrayList<>();

        instructionsList.add(new Instructions("Abdominal Pain", "Treatment For Symptoms:", ".GERD - Heartburn" +
                "from gastroesophagel reflux disease. Take an over the counter antacid.\n .Constipation - Take a laxative or mind stool softener.\n" +
                ".Pain - Take acetaminophen(Aspirin Free Anacin, Liquiprin,Panadol,Tylenol) and Avoid aspirin and ibuprofen(Advil,Midol,Motrin)"));
        instructionsList.add(new Instructions("Animal Bites", "Treatment for Symptopms", ".Clean wound with soap and warm water." +
                "Rinse for serveral minute after cleaning.\n.Reduce infection for wound to apply antibiotic cream.\n.Cover the wound with a sterile bandage.\n.If bleeding" +
                "continues, apply direct pressure until bleeding stops."));
        instructionsList.add(new Instructions("Animal Bites", "Treatment for Symptopms", ".Clean wound with soap and warm water." +
                "Rinse for serveral minute after cleaning.\n.Reduce infection for wound to apply antibiotic cream.\n.Cover the wound with a sterile bandage.\n.If bleeding" +
                "continues, apply direct pressure until bleeding stops."));
        instructionsList.add(new Instructions("Animal Bites", "Treatment for Symptopms", ".Clean wound with soap and warm water." +
                "Rinse for serveral minute after cleaning.\n.Reduce infection for wound to apply antibiotic cream.\n.Cover the wound with a sterile bandage.\n.If bleeding" +
                "continues, apply direct pressure until bleeding stops."));
        instructionsList.add(new Instructions("Animal Bites", "Treatment for Symptopms", ".Clean wound with soap and warm water." +
                "Rinse for serveral minute after cleaning.\n.Reduce infection for wound to apply antibiotic cream.\n.Cover the wound with a sterile bandage.\n.If bleeding" +
                "continues, apply direct pressure until bleeding stops."));
        instructionsList.add(new Instructions("Animal Bites", "Treatment for Symptopms", ".Clean wound with soap and warm water." +
                "Rinse for serveral minute after cleaning.\n.Reduce infection for wound to apply antibiotic cream.\n.Cover the wound with a sterile bandage.\n.If bleeding" +
                "continues, apply direct pressure until bleeding stops."));
    }
}
