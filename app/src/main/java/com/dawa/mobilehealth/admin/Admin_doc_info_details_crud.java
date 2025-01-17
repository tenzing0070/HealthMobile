package com.dawa.mobilehealth.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dawa.api.admin_api;
import com.dawa.mobilehealth.MainActivity;
import com.dawa.mobilehealth.R;
import com.dawa.mobilehealth.login.LoginActivity;
import com.dawa.model.doctors;
import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_doc_info_details_crud extends AppCompatActivity {

    TextView firstname, lastname, gender, specialist,price;
    CircleImageView imgProfile;
    ImageView docinfodelete;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_doc_info_details_crud);

        firstname = findViewById(R.id.doctor_firstname);
        lastname = findViewById(R.id.doctor_lastname);
        gender = findViewById(R.id.doctor_gender);
        specialist = findViewById(R.id.doctor_specialist);
        price = findViewById(R.id.doctor_price);
        imgProfile = findViewById(R.id.imgdocinfo_crud);

        docinfodelete = findViewById(R.id.imgdeletedocinfo);

        //retriving single data through recycle view from admincategories adapter //

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            firstname.setText(bundle.getString("firstname"));
            lastname.setText(bundle.getString("lastname"));
            gender.setText(bundle.getString("gender"));
            specialist.setText(bundle.getString("specialist"));
            price.setText(bundle.getString("price"));
            String imagepath = url.BASE_URL + bundle.getString("image");
            Picasso.get().load(imagepath).into(imgProfile);
        }

        docinfodelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deletedocinfo();
            }

            private void deletedocinfo() {
                admin_api docApi = url.getInstance().create(admin_api.class);
            Call<doctors> voidCall = docApi.deletePost(url.token, id);
            voidCall.enqueue(new Callback<doctors>() {
                @Override
                public void onResponse(Call<doctors> call, Response<doctors> response) {
                    if (!response.isSuccessful()) {

                        Toast.makeText(Admin_doc_info_details_crud.this, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();

                    }
                    Toast.makeText(Admin_doc_info_details_crud.this, "Deleted Doctor Details Successfully !!!", Toast.LENGTH_SHORT).show();
                    Intent openDocinfo = new Intent(Admin_doc_info_details_crud.this, DoctorInfoActivity.class);
                    startActivity(openDocinfo);
                }

                @Override
                public void onFailure(Call<doctors> call, Throwable t) {
                    Toast.makeText(Admin_doc_info_details_crud.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });


            }
        });
    }

    public void OpenDocinfoadmin(View view) {
        Intent opendocinfoadmin = new Intent(this, DoctorInfoActivity.class);
        startActivity(opendocinfoadmin);
    }


}