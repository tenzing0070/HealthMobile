package com.dawa.mobilehealth.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dawa.api.admin_api;
import com.dawa.mobilehealth.R;
import com.dawa.model.doctors;
import com.dawa.model.users;
import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_user_info_details_crud extends AppCompatActivity {

    TextView firstname, lastname, age,gender,address,email,
            phone,username,weight,height,bloodgroup;
    CircleImageView imguser;
    ImageView userinfodelete;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_info_details_crud);

        firstname = findViewById(R.id.user_firstname);
        lastname = findViewById(R.id.user_lastname);
        age = findViewById(R.id.user_age);
        gender = findViewById(R.id.user_gender);
        address = findViewById(R.id.user_address);
        email = findViewById(R.id.user_email);
        phone = findViewById(R.id.user_phone);
        username = findViewById(R.id.user_username);
        weight = findViewById(R.id.user_weight);
        height = findViewById(R.id.user_height);
        bloodgroup = findViewById(R.id.user_bloodgroup);
        imguser = findViewById(R.id.imguserimage);

        userinfodelete = findViewById(R.id.imgdeleteuserinfo);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            firstname.setText(bundle.getString("firstname"));
            lastname.setText(bundle.getString("lastname"));
            address.setText(bundle.getString("address"));
            age.setText(bundle.getString("age"));
            phone.setText(bundle.getString("phone"));
            email.setText(bundle.getString("email"));
            gender.setText(bundle.getString("gender"));
            weight.setText(bundle.getString("weight"));
            height.setText(bundle.getString("height"));
            bloodgroup.setText(bundle.getString("bloodgroup"));
            username.setText(bundle.getString("username"));
            String imagepath = url.BASE_URL + bundle.getString("image");
            Picasso.get().load(imagepath).into(imguser);
        }

        userinfodelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteuserinfo();
            }

            private void deleteuserinfo() {

                admin_api userinfoApi = url.getInstance().create(admin_api.class);
                Call<users> voidCall = userinfoApi.deleteUserInfo(url.token, id);
                voidCall.enqueue(new Callback<users>() {
                    @Override
                    public void onResponse(Call<users> call, Response<users> response) {
                        if (!response.isSuccessful()) {

                            Toast.makeText(Admin_user_info_details_crud.this, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();

                        }
                        Toast.makeText(Admin_user_info_details_crud.this, "Deleted User Details Successfullt !!!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<users> call, Throwable t) {
                        Toast.makeText(Admin_user_info_details_crud.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}