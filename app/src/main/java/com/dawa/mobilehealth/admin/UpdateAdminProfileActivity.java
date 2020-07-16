package com.dawa.mobilehealth.admin;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.dawa.api.health_api;
import com.dawa.mobilehealth.R;
import com.dawa.model.users;
import com.dawa.server_response.ImageResponse;
import com.dawa.strictmode.StrictModeClass;
import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAdminProfileActivity extends AppCompatActivity {

    private Button btnUpdate;
    private EditText firstname, lastname, address, age, gender, email, phone, username;
    ImageView imgProfile;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 200;
    String imagePath,imageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admin_profile);

        btnUpdate = findViewById(R.id.btnUpdateAdminProfile);

        firstname = findViewById(R.id.txtfirstname);
        lastname = findViewById(R.id.txtlastname);
        address = findViewById(R.id.txtaddress);
        age = findViewById(R.id.txtage);
        phone = findViewById(R.id.txtphone);
        email = findViewById(R.id.txtemail);
        gender = findViewById(R.id.txtgender);
        imgProfile=findViewById(R.id.imgAdminPic);
        username = findViewById(R.id.txtusername);

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        openuserdetails();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadAdminFile();
                updateAdmin();
            }
        });
    }

    private void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data!=null) {
                Uri uri = data.getData();
                imgProfile.setImageURI(uri);
                imagePath = getPath(uri);
            } else {
                Toast.makeText(UpdateAdminProfileActivity.this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(UpdateAdminProfileActivity.this, "image couldnot upload", Toast.LENGTH_SHORT).show();
        }
    }

    private String getPath(Uri uri){
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(UpdateAdminProfileActivity.this.getApplicationContext(), uri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String res = cursor.getString(colIndex);
        cursor.close();
        return res;
    }

    private void uploadAdminFile(){
        if(imagePath!=null){
            File file = new File(imagePath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);

            health_api mhealthApi = url.getInstance().create(health_api.class);
            Call<ImageResponse> responseBodyCall = mhealthApi.uploadImage(body);
            StrictModeClass.StrictMode();
            try {
                Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
                imageName = imageResponseResponse.body().getFilename();
            }catch (IOException e){
                Toast.makeText(UpdateAdminProfileActivity.this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }else {
            Toast.makeText(UpdateAdminProfileActivity.this, "Please choose file to update picture", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateAdmin() {
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
//                if( firstname.getText().toString().length() == 0 )
//                    firstname.setError( "First name is required!" );
                lastname.setText(response.body().getLastname());
//                if( lastname.getText().toString().length() == 0 )
//                    lastname.setError( "Last name is required!" );
                address.setText(response.body().getAddress());
//                if( address.getText().toString().length() == 0 )
//                    address.setError( "Address is required!" );
                age.setText(response.body().getAge());
//                if( age.getText().toString().length() == 0 )
//                    age.setError( "Age is required!" );
                phone.setText(response.body().getPhone());
//                if( phone.getText().toString().length() == 0 )
//                    phone.setError( "Phone is required!" );
                gender.setText(response.body().getGender());
//                if( gender.getText().toString().length() == 0 )
//                    gender.setError( "Gender is required!" );
                email.setText(response.body().getEmail());
//                if( email.getText().toString().length() == 0 )
//                    email.setError( "Email is required!" );
                username.setText(response.body().getUsername());
//                if( username.getText().toString().length() == 0 )
//                    username.setError( "Username is required!" );

                Toast.makeText(UpdateAdminProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(UpdateAdminProfileActivity.this, "Error" + t.getMessage(),Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(UpdateAdminProfileActivity.this, "code" + response.code(), Toast.LENGTH_SHORT).show();
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
                //image update
                String imagepath = url.BASE_URL + response.body().getImage();
                Picasso.get().load(imagepath).into(imgProfile);

            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(UpdateAdminProfileActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void AdminDashOpen(View view) {
        Intent admindashopen = new Intent(this, AdmindashActivity.class);
        startActivity(admindashopen);
    }


}


