package com.dawa.mobilehealth.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dawa.api.health_api;
import com.dawa.fragment.ProfileFragment;
import com.dawa.mobilehealth.MainActivity;
import com.dawa.mobilehealth.R;
import com.dawa.model.users;
import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends Fragment {

    private Button btnUpdate;
    private EditText firstname, lastname, address, age, gender, email, phone, username;
    ImageView imgProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_update_profile, container, false);
        btnUpdate = view.findViewById(R.id.btnUpdateProfile);

        firstname = view.findViewById(R.id.txtfirstname);
        lastname = view.findViewById(R.id.txtlastname);
        address = view.findViewById(R.id.txtaddress);
        age = view.findViewById(R.id.txtage);
        phone = view.findViewById(R.id.txtphone);
        email = view.findViewById(R.id.txtemail);
        gender = view.findViewById(R.id.txtgender);
        imgProfile=view.findViewById(R.id.imgProfilee);
        username = view.findViewById(R.id.txtusername);

        openuserdetails();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }

        });
        return view;
    }

    private void updateUser() {
        users users = new users(
                firstname.getText().toString(),
                lastname.getText().toString(),
                address.getText().toString(),
                age.getText().toString(),
                phone.getText().toString(),
                email.getText().toString(),
                gender.getText().toString(),
                username.getText().toString()
        );

        health_api registerUpdateApi = url.getInstance().create(health_api.class);
        Call<users> registerUpdateCall = registerUpdateApi.updateUser(url.token, users);
        registerUpdateCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {

                firstname.setText(response.body().getFirstname());
                lastname.setText(response.body().getLastname());
                address.setText(response.body().getAddress());
                age.setText(response.body().getAge());
                phone.setText(response.body().getPhone());
                gender.setText(response.body().getGender());
                email.setText(response.body().getEmail());
                username.setText(response.body().getUsername());

                Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" + t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void openuserdetails() {

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
                address.setText(response.body().getAddress());
                age.setText(response.body().getAge());
                phone.setText(response.body().getPhone());
                email.setText(response.body().getEmail());
                gender.setText(response.body().getGender());
                username.setText(response.body().getUsername());
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}
