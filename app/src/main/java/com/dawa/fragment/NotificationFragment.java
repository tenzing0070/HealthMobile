package com.dawa.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawa.adapter.NotificationAdapter;
import com.dawa.api.doctor_api;
import com.dawa.mobilehealth.R;
import com.dawa.model.Booking;
import com.dawa.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationFragment extends Fragment {

    NotificationFragment fragment;
private RecyclerView recyclerView;

    NotificationAdapter notification_Adapter;

    public NotificationFragment() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_notification, container, false);

        fragment = this;
        recyclerView = v.findViewById(R.id.notification_list);

        loadnotification();
        return v;
    }
    private void loadnotification() {

        doctor_api doctorAPI = url.getInstance().create(doctor_api.class);
        Call<List<Booking>> notificationCall = doctorAPI.getBooking(url.token);


        notificationCall.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }

                List<Booking> bookingsList = response.body();
               fragment.notification_Adapter = new NotificationAdapter(getActivity(), bookingsList);
                recyclerView.setAdapter(notification_Adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));

            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {

            }
        });
    }
}
