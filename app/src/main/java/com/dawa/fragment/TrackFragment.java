package com.dawa.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.dawa.mobilehealth.FootStepsActivity;
import com.dawa.mobilehealth.R;

public class TrackFragment extends Fragment {

    public TrackFragment() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_track, container, false);

        Button btnStepsCount = v.findViewById(R.id.btnFootSteps);
        btnStepsCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent openStepCount = new Intent(getActivity(),FootStepsActivity.class);
            startActivity(openStepCount);
            }
        });

        return v;
    }
}

