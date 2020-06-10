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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.dawa.api.health_api;
import com.dawa.mobilehealth.FaqActivity;
import com.dawa.mobilehealth.FeedbackActivity;
import com.dawa.mobilehealth.R;

import com.dawa.mobilehealth.login.LoginActivity;
import com.dawa.mobilehealth.login.UpdateProfileActivity;
import com.dawa.model.users;
import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private TextView firstname, lastname;
    ImageView imgProfile;

    public ProfileFragment() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        firstname = v.findViewById(R.id.txtfirstname);
        lastname = v.findViewById(R.id.txtlastname);
        imgProfile = v.findViewById(R.id.imgProfilee);

        openuserinfo();

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new UpdateProfileActivity()).commit();
            }
        });

        Button btnMyProfile = v.findViewById(R.id.btnMyProfile);
        btnMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new UpdateProfileActivity()).commit();

            }
        });
        //FAQ
        Button btnFaq = v.findViewById(R.id.btnFaq);
        btnFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new FaqActivity()).commit();
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
//
//         Feedback
        Button btnFeedback = v.findViewById(R.id.btnFeedback);
        btnFeedback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new FeedbackActivity()).commit();
            }
        });

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
                    lastname.setText(response.body().getLastname());

                }

                @Override
                public void onFailure(Call<users> call, Throwable t) {
                    Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }


    }





