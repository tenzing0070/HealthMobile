//package com.dawa.mobilehealth;
//
//import android.os.Bundle;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.dawa.adapter.NotificationAdapter;
//import com.dawa.api.doctor_api;
//import com.dawa.model.Booking;
//
//import com.dawa.url.url;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class NotificationActivity extends AppCompatActivity {
//
//    NotificationActivity activity;
//    private RecyclerView recyclerView;
//
//    NotificationAdapter notification_Adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_notification);
//        activity = this;
//
//        recyclerView = findViewById(R.id.notification_list);
//
//        loadnotification();
//
//    }
//
//    private void loadnotification() {
//
//        doctor_api doctorAPI = url.getInstance().create(doctor_api.class);
//        Call<List<Booking>> notificationCall = doctorAPI.getBooking(url.token);
//
//
//        notificationCall.enqueue(new Callback<List<Booking>>() {
//            @Override
//            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
//                if(!response.isSuccessful()) {
//                    Toast.makeText(NotificationActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                }
//
//                List<Booking> bookingsList = response.body();
//                activity.notification_Adapter = new NotificationAdapter(NotificationActivity.this, bookingsList);
//                recyclerView.setAdapter(notification_Adapter);
//                recyclerView.setLayoutManager(new GridLayoutManager(NotificationActivity.this,1));
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Booking>> call, Throwable t) {
//
//            }
//        });
//    }
//}