package com.poll.mobilehealthwearable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.poll.Url.url;
import com.poll.api.doctor_api;
import com.poll.api.health_api;
import com.poll.model.Booking;
import com.poll.model.users;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoctorDetailActivity extends Activity {


    private EditText edpurpose, bokdate, boktime;
    private Button btnbooking;
    TextView firstName, lastName, specialist, gender, price;
    String id;
    CircleImageView imgProfileDoc;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        btnbooking = findViewById(R.id.btncontinue_booking);
        edpurpose = findViewById(R.id.txtPurpose);
        bokdate = findViewById(R.id.etDate);
        String date_n = new SimpleDateFormat("MM dd, yyyy", Locale.getDefault()).format(new Date());
        TextView date  = (TextView) findViewById(R.id.etDate);
        date.setText(date_n);

        boktime = findViewById(R.id.etTime);

        String date_t = new SimpleDateFormat("HH:ss", Locale.getDefault()).format(new Date());
        TextView time  = (TextView) findViewById(R.id.etTime);
        time.setText(date_t);

        firstName = findViewById(R.id.doctorFirstName);
        lastName = findViewById(R.id.doctorLastName);
        specialist = findViewById(R.id.doctorSpecialist);
         gender = findViewById(R.id.doctortextGender);
        price = findViewById(R.id.viewholder_doctor_price);
        imgProfileDoc = findViewById(R.id.imgProfileDoc);

        loaduser();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            firstName.setText(bundle.getString("firstname"));
            lastName.setText(bundle.getString("lastname"));
            specialist.setText(bundle.getString("Specialist"));
             gender.setText(bundle.getString("gender"));
          price.setText(bundle.getString("price"));
            Picasso.get().load(url.imagePath+bundle.getString("image")).into(imgProfileDoc);

        }

  btnbooking.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          book();
          openNoti();

      }
  });

    }

    private void openNoti() {
        Toast.makeText(this,"Booked Successfully", Toast.LENGTH_SHORT).show();
        Intent openNoti = new Intent(this, NotificationActivity.class);
        startActivity(openNoti);
    }

    private void loaduser() {

        health_api healthApi = url.getInstance().create(health_api.class);
        Call<users> bookingCall = healthApi.getUserDetails(url.token);

        bookingCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(DoctorDetailActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                name = response.body().getUsername();
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {


                Toast.makeText(DoctorDetailActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void book(){
        String purpose = edpurpose.getText().toString();
        if( edpurpose.getText().toString().length() == 0 ) {
            edpurpose.setError("Purpose field is required!");
        }
        String date = bokdate.getText().toString();
        if( bokdate.getText().toString().length() == 0 ) {
            bokdate.setError("Date field is required!");
        }
        String time = boktime.getText().toString();
        if( boktime.getText().toString().length() == 0 ) {
            boktime.setError("Time field is required!");
        }

            String doctors = id;

            doctor_api doctorApi = url.getInstance().create(doctor_api.class);

            Call<Booking> bookingCall = doctorApi.book(url.token, doctors, purpose, date, time);

            bookingCall.enqueue(new Callback<Booking>() {
                @Override
                public void onResponse(Call<Booking> call, Response<Booking> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(DoctorDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Toast.makeText(DoctorDetailActivity.this, "Booked Successfully", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<Booking> call, Throwable t) {

                }
            });
        }



    public void UserDashOpen(View view) {
        Intent openUserDash = new Intent(this, Dashboard.class);
        startActivity(openUserDash);
    }

    public void OpenDocApp(View view) {
        Intent opendocapp = new Intent(this, DocAppointmentActivity.class);
        startActivity(opendocapp);
    }

}






















