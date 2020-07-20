package com.dawa.mobilehealth.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dawa.api.admin_api;
import com.dawa.mobilehealth.R;
import com.dawa.model.Booking;
import com.dawa.model.doctors;
import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_booking_info_details_crud extends AppCompatActivity {

    TextView username, purpose, date, time, docfirstname, doclastname, docgender,
    docprice, docspecialist;
    CircleImageView imgProfile;
    ImageView bookinginfodelete;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_booking_info_details_crud);

        username = findViewById(R.id.user_appoint_username);
        purpose = findViewById(R.id.appoint_Purpose);
        date = findViewById(R.id.appoint_Date);
        time = findViewById(R.id.appoint_Time);
        docfirstname = findViewById(R.id.doc_appoint_Fname);
        doclastname = findViewById(R.id.doc_appoint_Lname);
        docgender = findViewById(R.id.doc_appoint_Gender);
        docprice = findViewById(R.id.doc_appoint_Price);
        docspecialist = findViewById(R.id.doc_appoint_Specialist);
        imgProfile = findViewById(R.id.imgProfileDocAppoint);

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
                admin_api bookingApi = url.getInstance().create(admin_api.class);
                Call<Booking> voidCall = bookingApi.deleteBookingPost(url.token, id);
                voidCall.enqueue(new Callback<Booking>() {
                    @Override
                    public void onResponse(Call<Booking> call, Response<Booking> response) {
                        if (!response.isSuccessful()) {

                            Toast.makeText(Admin_booking_info_details_crud.this, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();

                        }
                        Toast.makeText(Admin_booking_info_details_crud.this, "Deleted Appointment Details Successfully !!!", Toast.LENGTH_SHORT).show();
                        Intent openAppointinfo = new Intent(Admin_booking_info_details_crud.this, AppointmentInfoActivity.class);
                        startActivity(openAppointinfo);
                    }

                    @Override
                    public void onFailure(Call<Booking> call, Throwable t) {
                        Toast.makeText(Admin_booking_info_details_crud.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

    public void OpenAppointmentinfoadmin(View view) {
        Intent openbookinfoadmin = new Intent(this, AppointmentInfoActivity.class);
        startActivity(openbookinfoadmin);
    }
}