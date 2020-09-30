package com.dawa.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.dawa.api.health_api;
import com.dawa.mobilehealth.BmiActivity;
import com.dawa.mobilehealth.DocAppointmentActivity;
import com.dawa.mobilehealth.FootStepsActivity;
import com.dawa.mobilehealth.QrScanActivity;
import com.dawa.mobilehealth.Quiz.QuizDashActivity;
import com.dawa.mobilehealth.R;
import com.dawa.mobilehealth.StopwatchActivity;
import com.dawa.mobilehealth.UpdateHealthRecordActivity;
import com.dawa.mobilehealth.heart.HeartRateMonitorActivity;
import com.dawa.mobilehealth.login.UpdateProfileActivity;
import com.dawa.model.users;
import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    ImageView imgProfile, imgstopwatch, imgemergencycall, imghealthrecord, imgqrscan;
    private TextView firstname;
    LinearLayout llfootstep, llbmiscale, lldocappointment, llheartmonitor, llhealthquiz;

    public HomeFragment() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        firstname = v.findViewById(R.id.txtfirstname);
        imgProfile = v.findViewById(R.id.imgProfilee);
        imgstopwatch = v.findViewById(R.id.imgStopwatch);
        imghealthrecord = v.findViewById(R.id.imgHealthrecord);
        imgemergencycall = v.findViewById(R.id.imgEmergencyCall);
        imgqrscan = v.findViewById(R.id.imgQrScan);
        llfootstep = v.findViewById(R.id.llFootstep);
        llbmiscale = v.findViewById(R.id.llBmiScale);
        lldocappointment = v.findViewById(R.id.llDocAppointment);
        llheartmonitor = v.findViewById(R.id.llHeartMonitor);
        llhealthquiz = v.findViewById(R.id.llHealthQuiz);
        openuserinfo();
        //UserProfile
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,
                        new UpdateProfileActivity()).commit();
            }
        });

        //Stopwatch
        imgstopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,
                        new StopwatchActivity()).commit();
            }
        });

        //Health Record
        imghealthrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,
                        new UpdateHealthRecordActivity()).commit();
            }
        });

        //QR SCAN
        imgqrscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openqrscan = new Intent(getActivity(), QrScanActivity.class);
                startActivity(openqrscan);
            }
        });

        //Emergency Call
        imgemergencycall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initiatePhoneCall();
            }
        });

        //Foot Steps
        llfootstep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,
                        new FootStepsActivity()).commit();
//                Intent openStepCount = new Intent(getActivity(), FootStepsActivity.class);
//                startActivity(openStepCount);
            }
        });
        //BMI
        llbmiscale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,
                        new BmiActivity()).commit();
            }
        });

        //Doctor Appointment
        lldocappointment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent openDocAppointment = new Intent(getActivity(), DocAppointmentActivity.class);
                startActivity(openDocAppointment);
            }
        });

        //Heart Monitor
        llheartmonitor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent openHeartDash = new Intent(getActivity(), HeartRateMonitorActivity.class);
                startActivity(openHeartDash);
            }
        });

        //Quiz health
        llhealthquiz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent openHealthQuiz = new Intent(getActivity(), QuizDashActivity.class);
                startActivity(openHealthQuiz);

            }
        });


        return v;
    }

    private void initiatePhoneCall() {
        String number = "100";
        Intent callUsIntent = new Intent(Intent.ACTION_CALL);
        callUsIntent.setData(Uri.parse("tel:" + number));
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        startActivity(callUsIntent);
    }

    private void openuserinfo() {

        health_api hrsApi = url.getInstance().create(health_api.class);
        Call<users> usersCall = hrsApi.getUserDetails(url.token);
        System.out.println("token is:"+url.token);

        usersCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if(response.body().getImage()!=null) {
                    String imgpath = url.BASE_URL + response.body().getImage();
                    System.out.println("image response is :"+imgpath);

                    Picasso.get().load(imgpath).into(imgProfile);
                }
                else
                {
                    Picasso.get().load(R.drawable.image1).into(imgProfile);
                }
                firstname.setText(response.body().getFirstname());
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}






