package com.poll.mobilehealthwearable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.poll.Url.url;
import com.poll.api.health_api;
import com.poll.model.users;
import com.squareup.picasso.Picasso;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateHealthRecordActivity extends Activity {

    private Button  btnQrGenerate;
    private TextView firstname, lastname, age, address, phone, gender, email, weight, height, bloodgroup;
    ImageView imgProfile, qrImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_record);

        btnQrGenerate = findViewById(R.id.btnQrGenerate);
        qrImage = findViewById(R.id.qrPlaceHolder);

        firstname =findViewById(R.id.txtfirstname);
        lastname = findViewById(R.id.txtlastname);
        age = findViewById(R.id.txtage);
        address = findViewById(R.id.txtaddress);
        phone = findViewById(R.id.txtphone);
        gender = findViewById(R.id.txtgender);
        email = findViewById(R.id.txtemail);
        weight = findViewById(R.id.txtweight);
        height = findViewById(R.id.txtheight);
        bloodgroup = findViewById(R.id.txtbloodgroup);
        imgProfile = findViewById(R.id.imgProfilee);

        showuserdetails();

        btnQrGenerate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String fnamedata = "First Name:" + firstname.getText().toString();
                String lnamedata = "Last Name:" + lastname.getText().toString();
                String agedata = "Age: " + age.getText().toString();
                String addressdata = "Address:" + address.getText().toString();
                String phonedata = "Phone:" + phone.getText().toString();
                String genderdata = "Gender:" + gender.getText().toString();
                String emaildata = "Email:" + email.getText().toString();
                String weightdata = "Weight:" + weight.getText().toString();
                String heightdata = "Height:" + height.getText().toString();
                String bloodgroupdata = "BloodType:" + bloodgroup.getText().toString();

                QRGEncoder qrgEncoder = new QRGEncoder( fnamedata + "\n" +    lnamedata + "\n" + agedata + "\n" +
                        addressdata + "\n" + phonedata + "\n" + genderdata + "\n" + emaildata + "\n"  + weightdata + "\n" +
                        heightdata + "\n" + bloodgroupdata,  null, QRGContents.Type.TEXT,500 );
                Bitmap qrBits = qrgEncoder.getBitmap();
                qrImage.setImageBitmap(qrBits);
            }
        });

    }


    private void showuserdetails(){

        health_api hrsApi = url.getInstance().create(health_api.class);
        Call<users> usersCall = hrsApi.getUserDetails(url.token);
        System.out.println("token is:"+url.token);

        usersCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UpdateHealthRecordActivity.this, "code" + response.code(), Toast.LENGTH_SHORT).show();
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
                age.setText(response.body().getAge());
                address.setText(response.body().getAddress());
                phone.setText(response.body().getPhone());
                gender.setText(response.body().getGender());
                email.setText(response.body().getEmail());
                weight.setText(response.body().getWeight());
                height.setText(response.body().getHeight());
                bloodgroup.setText(response.body().getBloodgroup());

            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(UpdateHealthRecordActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void OpenAdminDashboard(View view) {
        Intent openWearableDash = new Intent(this, Dashboard.class);
        startActivity(openWearableDash);
    }
}