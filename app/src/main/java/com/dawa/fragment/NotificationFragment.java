package com.dawa.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.dawa.mobilehealth.R;




public class NotificationFragment extends Fragment {

//    TextView firstname, lastname, specialist, gender, price, username, date, time;
//
//    String id;

    public NotificationFragment() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_notification, container, false);

//        firstname =  v.findViewById(R.id.doc_noti_Fname);
//        lastname =  v.findViewById(R.id.doc_noti_Lname);
//        specialist =  v.findViewById(R.id.doc_noti_Specialist);
//        gender =  v.findViewById(R.id.doc_noti_Gender);
//        price =  v. findViewById(R.id.doc_noti_Price);
//
//        username =  v.findViewById(R.id.user_noti_username);
//        date = v.findViewById(R.id.noti_Date);
//        time =  v.findViewById(R.id.noti_Time);
//
//        doctor_api notification = url.getInstance().create(doctor_api.class);
//        Call<List<Booking>> notificationCall = notification.getBooking(url.token);
//        notificationCall.enqueue(new Callback<List<Booking>>() {
//            @Override
//            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
//
//                id = response.body().get(0).getId();
//                firstname.setText(response.body().get(0).getDoctors().getFirstname());
//                lastname.setText(response.body().get(0).getDoctors().getLastname());
//                specialist.setText(response.body().get(0).getDoctors().getSpecialist());
//                gender.setText(response.body().get(0).getDoctors().getGender());
//                price.setText(response.body().get(0).getDoctors().getPrice());
//
//                username .setText(response.body().get(0).getUser().getUsername());
//                time .setText(response.body().get(0).getTime());
//                date .setText(response.body().get(0).getDate());
//
//                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Booking>> call, Throwable t) {
//                Toast.makeText(getActivity(), "Error" + t.getMessage(),Toast.LENGTH_SHORT).show();
//
//            }
//        });


        return v;
    }
}
