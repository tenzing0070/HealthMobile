package com.dawa.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.dawa.mobilehealth.R;
import com.dawa.mobilehealth.login.LoginActivity;
import com.dawa.mobilehealth.login.UpdateProfileActivity;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container, false);


        Button btnMyProfile = v.findViewById(R.id.btnMyProfile);
        btnMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new UpdateProfileActivity()).commit();

            }
        });


//        Button btnMap = v.findViewById(R.id.btnlocation);
//        btnMap.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MapsActivity()).commit();
//            }
//        });


//        Button btnFeedback = v.findViewById(R.id.btnFeedback);
//        btnFeedback.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent openFeedback = new Intent(getActivity(),FeedbackActivity.class);
//                startActivity(openFeedback);
//            }
//        });


        Button btnlogout = v.findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(false);
                builder.setMessage("Do you want to Logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        SharedPreferences rememberData = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
                        SharedPreferences rememberToken = getActivity().getSharedPreferences("userToken", MODE_PRIVATE);
                        rememberData.edit().clear().commit();
                        rememberToken.edit().clear().commit();
                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        startActivity(i);

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }

        });


        return v;
    }
}



