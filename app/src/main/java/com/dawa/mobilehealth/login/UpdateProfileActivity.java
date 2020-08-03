package com.dawa.mobilehealth.login;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import static android.app.Activity.RESULT_OK;

public class UpdateProfileActivity extends Fragment {

    private Button btnUpdate;
    private EditText firstname, lastname, address, age, gender, email, phone;
    private TextView username;
    ImageView imgProfile;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 200;
    String imagePath,imageName;
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
                uploadFile();
                updateUser();
            }

        });
        return view;
    }

    //image upload from here
    private void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    private void uploadFile(){
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
                Toast.makeText(getActivity(), "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }else {
            Toast.makeText(getActivity(), "Please choose file to update picture", Toast.LENGTH_SHORT).show();
        }
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
                Toast.makeText(getActivity(), "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "image couldnot upload", Toast.LENGTH_SHORT).show();
        }
    }

    private String getPath(Uri uri){
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getActivity().getApplicationContext(), uri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String res = cursor.getString(colIndex);
        cursor.close();
        return res;
    }

    //image upload till here

    private void updateUser() {

        users users = new users(
                firstname.getText().toString(),
                lastname.getText().toString(),
                address.getText().toString(),
                age.getText().toString(),
                phone.getText().toString(),
                email.getText().toString(),
                gender.getText().toString(),
                username.getText().toString(), imageName, 1

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
                //image update
                String imagepath = url.BASE_URL + response.body().getImage();
                Picasso.get().load(imagepath).into(imgProfile);

            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
