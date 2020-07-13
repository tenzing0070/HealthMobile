package com.dawa.mobilehealth.login;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.dawa.api.health_api;
import com.dawa.mobilehealth.R;
import com.dawa.model.users;
import com.dawa.server_response.ImageResponse;
import com.dawa.server_response.SignUpResponse;
import com.dawa.strictmode.StrictModeClass;
import com.dawa.url.url;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity  extends AppCompatActivity {

    private CircleImageView imgProfile;
    private EditText etfirstname, etlastname, etaddress, etage, etgender, etweight, etheight,etbloodgroup,etemail, etphone, etusername, etpassword, etConfirmPassword;
    private Button btnsignup;
    String imagePath;
    private String imageName = "";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_signup);

        imgProfile = findViewById(R.id.imgProfile);
        etfirstname = findViewById(R.id.txtfirstname);
        etlastname = findViewById(R.id.txtlastname);
        etaddress = findViewById(R.id.txtaddress);
        etage = findViewById(R.id.txtage);
        etphone = findViewById(R.id.txtphone);
        etgender = findViewById(R.id.txtgender);
        etweight = findViewById(R.id.txtweight);
        etheight = findViewById(R.id.txtheight);
        etbloodgroup = findViewById(R.id.txtbloodgroup);
        etemail = findViewById(R.id.txtemail);
        etusername = findViewById(R.id.txtusername);
        etpassword = findViewById(R.id.txtpassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnsignup = findViewById(R.id.btnsignup);


        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etpassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                    if(validate()) {
                       // saveImageOnly();
                        signUp();
                    }
                } else {
                    Toast.makeText(SignupActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    etpassword.requestFocus();
                    return;
                }

            }


        });

    }

    private boolean validate() {
        boolean status=true;
        if (etusername.getText().toString().length() < 6) {
            etusername.setError("Minimum 6 character");
            status=false;
        }
        return status;
    }

    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgProfile.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

//    private void saveImageOnly() {
//        File file = new File(imagePath);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
//                file.getName(), requestBody);
//
//        health_api usersAPI = url.getInstance().create(health_api.class);
//        Call<ImageResponse> responseBodyCall = usersAPI.uploadImage(body);
//
//        StrictModeClass.StrictMode();
//        //Synchronous method
//        try {
//            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
//            imageName = imageResponseResponse.body().getFilename();
//            Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//    }

    private void signUp() {

        String fname = etfirstname.getText().toString();
        String lname = etlastname.getText().toString();
        String address = etaddress.getText().toString();
        String age = etage.getText().toString();
        String phone = etphone.getText().toString();
        String email = etemail.getText().toString();
        String gender = etgender.getText().toString();
        String weight = etweight.getText().toString();
        String height = etheight.getText().toString();
        String bloodgroup = etbloodgroup.getText().toString();
        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();

        users users = new users(fname, lname, address, age, phone, email, gender, weight, height, bloodgroup, username, password, imageName);

        health_api usersAPI = url.getInstance().create(health_api.class);
        Call<SignUpResponse> signUpCall = usersAPI.signup(users);

        signUpCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(SignupActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void OpenLogin(View view) {
        Intent openLogin = new Intent(this, LoginActivity.class);
        startActivity(openLogin);
    }
}
