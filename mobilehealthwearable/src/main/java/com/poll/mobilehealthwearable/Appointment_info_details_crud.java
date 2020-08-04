package com.poll.mobilehealthwearable;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.poll.Url.url;

import com.poll.api.health_api;
import com.poll.model.Booking;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Appointment_info_details_crud extends Activity {


    TextView username, purpose, date, time, docfirstname, doclastname, docgender,
            docprice, docspecialist;
    CircleImageView imgProfile;
    ImageView bookinginfodelete;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_notification_crud);

        username = findViewById(R.id.user_noti_username);
        purpose = findViewById(R.id.noti_Purpose);
        date = findViewById(R.id.noti_Date);
        time = findViewById(R.id.noti_Time);
        docfirstname = findViewById(R.id.doc_noti_Fname);
        doclastname = findViewById(R.id.doc_noti_Lname);
        docgender = findViewById(R.id.doc_noti_Gender);
        docprice = findViewById(R.id.doc_noti_Price);
        docspecialist = findViewById(R.id.doc_noti_Specialist);
        imgProfile = findViewById(R.id.imgProfileDoc);



        bookinginfodelete = findViewById(R.id.imgBookingDelete);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            username.setText(bundle.getString("username"));
            purpose.setText(bundle.getString("purpose"));
            date.setText(bundle.getString("date"));
            time.setText(bundle.getString("time"));
            docfirstname.setText(bundle.getString("firstname"));
            doclastname.setText(bundle.getString("lastname"));
            docgender.setText(bundle.getString("gender"));
            docprice.setText(bundle.getString("price"));
            docspecialist.setText(bundle.getString("specialist"));
            String imagepath = url.BASE_URL + bundle.getString("image");
            Picasso.get().load(imagepath).into(imgProfile);
        }

        bookinginfodelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletebookinfo();
            }

            private void deletebookinfo() {
                health_api bookingApi = url.getInstance().create(health_api.class);
                Call<Booking> voidCall = bookingApi.deleteBookingPost(url.token, id);
                voidCall.enqueue(new Callback<Booking>() {
                    @Override
                    public void onResponse(Call<Booking> call, Response<Booking> response) {
                        if (!response.isSuccessful()) {

                            Toast.makeText(Appointment_info_details_crud.this, "Deleted Appointment Details Successfully", Toast.LENGTH_SHORT).show();

                            Intent openAppointinfo = new Intent(Appointment_info_details_crud.this, NotificationActivity.class);
                            startActivity(openAppointinfo);

                        }
                        Toast.makeText(Appointment_info_details_crud.this, "Deleted Appointment Details Successfully !!!", Toast.LENGTH_SHORT).show();
                        Intent openAppointinfo = new Intent(Appointment_info_details_crud.this, NotificationActivity.class);
                        startActivity(openAppointinfo);
                    }

                    @Override
                    public void onFailure(Call<Booking> call, Throwable t) {
                        Toast.makeText(Appointment_info_details_crud.this, "Deleted Appointment Details Successfully", Toast.LENGTH_SHORT).show();
                        Intent openAppointinfo = new Intent(Appointment_info_details_crud.this, NotificationActivity.class);
                        startActivity(openAppointinfo);
                    }
                });


            }
        });
    }

    public void OpenNotification(View view) {
        Intent openNoti = new Intent(this, NotificationActivity.class);
        startActivity(openNoti);
    }

    }

