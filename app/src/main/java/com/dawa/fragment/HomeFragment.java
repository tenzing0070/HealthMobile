package com.dawa.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.dawa.mobilehealth.BmiActivity;
import com.dawa.mobilehealth.FeedbackActivity;
import com.dawa.mobilehealth.FootStepsActivity;
import com.dawa.mobilehealth.R;
import com.dawa.mobilehealth.StopwatchActivity;

public class HomeFragment extends Fragment {

    Button btn1;

    public HomeFragment() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        //Foot Steps
        Button btnStepsCount = v.findViewById(R.id.btnFootSteps);
        btnStepsCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openStepCount = new Intent(getActivity(), FootStepsActivity.class);
                startActivity(openStepCount);
            }
        });

        //BMI
        Button btnBmi = v.findViewById(R.id.btnBmi);
        btnBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new BmiActivity()).commit();
            }
        });

        //Stopwatch
        Button btnStopwatch = v.findViewById(R.id.btnStopwatch);
        btnStopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openWatch = new Intent(getActivity(), StopwatchActivity.class);
                startActivity(openWatch);
            }
        });

        return v;
    }
}






