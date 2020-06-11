package com.dawa.mobilehealth;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dawa.api.health_api;
import com.dawa.model.users;
import com.dawa.url.url;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateHealthRecordActivity extends Fragment {

    private Button btnUpdate, btnQrGenerate;
    private EditText weight,height,bloodgroup;
    private TextView firstname, lastname, age, address, phone, gender, email, weight1, height1,bloodgroup1;
    ImageView imgProfile, qrImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_health_record, container, false);
        btnUpdate = view.findViewById(R.id.btnUpdateHealthRecord);
        btnQrGenerate = view.findViewById(R.id.btnQrGenerate);
        qrImage = view.findViewById(R.id.qrPlaceHolder);

        firstname = view.findViewById(R.id.txtfirstname);
        lastname = view.findViewById(R.id.txtlastname);
        age = view.findViewById(R.id.txtage);
        address = view.findViewById(R.id.txtaddress);
        phone = view.findViewById(R.id.txtphone);
        gender = view.findViewById(R.id.txtgender);
        email = view.findViewById(R.id.txtemail);
        weight = view.findViewById(R.id.txtweight);
        height = view.findViewById(R.id.txtheight);
        bloodgroup = view.findViewById(R.id.txtbloodgroup);
        imgProfile = view.findViewById(R.id.imgProfilee);
        weight1 = view.findViewById(R.id.txtweight1);
        height1 = view.findViewById(R.id.txtheight1);
        bloodgroup1 = view.findViewById(R.id.txtbloodgroup1);

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
                String weightdata = "Weight:" + weight1.getText().toString();
                String heightdata = "Height:" + height1.getText().toString();
                String bloodgroupdata = "BloodType:" + bloodgroup1.getText().toString();

                QRGEncoder qrgEncoder = new QRGEncoder( fnamedata + "\n" +    lnamedata + "\n" + agedata + "\n" +
                        addressdata + "\n" + phonedata + "\n" + genderdata + "\n" + emaildata + "\n"  + weightdata + "\n" +
                        heightdata + "\n" + bloodgroupdata,  null, QRGContents.Type.TEXT,500 );
                Bitmap qrBits = qrgEncoder.getBitmap();
                qrImage.setImageBitmap(qrBits);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateHealthRecord();
            }
        });

        return view;
    }

    private void updateHealthRecord() {
        users usershealthrecord = new users(
                firstname.getText().toString(),
                 lastname.getText().toString(),
                age.getText().toString(),
                address.getText().toString(),
                phone.getText().toString(),
                gender.getText().toString(),
                email.getText().toString(),
                 weight.getText().toString(),
                 height.getText().toString(),
                bloodgroup.getText().toString()
        );

        health_api HealthUpdateApi = url.getInstance().create(health_api.class);
        Call<users> healthUpdateCall = HealthUpdateApi.updateUser(url.token, usershealthrecord);
        healthUpdateCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {

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
                weight1.setText(response.body().getWeight());
                height1.setText(response.body().getHeight());
                bloodgroup1.setText(response.body().getBloodgroup());

                Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
                weight.getText().clear();
                height.getText().clear();
                bloodgroup.getText().clear();
            }
            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" + t.getMessage(),Toast.LENGTH_SHORT).show();

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
                age.setText(response.body().getAge());
                address.setText(response.body().getAddress());
                phone.setText(response.body().getPhone());
                gender.setText(response.body().getGender());
                email.setText(response.body().getEmail());
                weight1.setText(response.body().getWeight());
                height1.setText(response.body().getHeight());
                bloodgroup1.setText(response.body().getBloodgroup());
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    }
