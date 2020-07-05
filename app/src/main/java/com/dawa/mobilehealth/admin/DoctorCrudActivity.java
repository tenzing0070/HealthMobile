package com.dawa.mobilehealth.admin;

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

import com.dawa.api.doctor_api;

import com.dawa.mobilehealth.R;


import com.dawa.model.doctors;

import com.dawa.server_response.ImageResponse;

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

public class DoctorCrudActivity extends AppCompatActivity {

    private CircleImageView imgAdminDoctorProfile;
    private EditText docFname, docLname, docSpecialist, docGender, docPrice;
    private Button btnAddDoc;
    String imagePath;
    private String imageName = "";
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 200;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_admin_doctor_crud);

        imgAdminDoctorProfile = findViewById(R.id.imgAdminDoctorProfile);
        docFname = findViewById(R.id.crud_doc_firstname);
        docLname = findViewById(R.id.crud_doc_lastname);
        docSpecialist = findViewById(R.id.crud_doc_specialist);
        docGender = findViewById(R.id.crud_doc_gender);
        docPrice = findViewById(R.id.crud_doc_price);
        btnAddDoc = findViewById(R.id.btnAddDoc);

        imgAdminDoctorProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
        btnAddDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageOnly();
                addDoc();
            }
        });
    }

    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data!=null) {
                Uri uri = data.getData();
                imgAdminDoctorProfile.setImageURI(uri);
                imagePath = getRealPathFromUri(uri);
            } else {
                Toast.makeText(DoctorCrudActivity.this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(DoctorCrudActivity.this, "image couldnot upload", Toast.LENGTH_SHORT).show();
        }
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

    private void saveImageOnly() {
       if (imagePath!=null) {
           File file = new File(imagePath);
           RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
           MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                   file.getName(), requestBody);

           doctor_api usersAPI = url.getInstance().create(doctor_api.class);
           Call<ImageResponse> responseBodyCall = usersAPI.DocImgUpload(url.token,body);

           StrictModeClass.StrictMode();
           //Synchronous method
           try {
               Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
               imageName = imageResponseResponse.body().getFilename();
               Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
           } catch (IOException e) {
               Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
               e.printStackTrace();
           }
       }
        else {

            Toast.makeText(DoctorCrudActivity.this, "Please insert picture", Toast.LENGTH_SHORT).show();
//            onStop();

        }
    }


    private void addDoc() {

        final String docfname = docFname.getText().toString();
        String doclname = docLname.getText().toString();
        String docspecialist = docSpecialist.getText().toString();

        String docgender = docGender.getText().toString();
        String docprice = docPrice.getText().toString();

        doctors doctors = new doctors(docfname, doclname, docspecialist, imageName,docgender, docprice);

        doctor_api docAPI = url.getInstance().create(doctor_api.class);
        Call<doctors> addDocCall = docAPI.add(doctors);

        addDocCall.enqueue(new Callback<doctors>() {
            @Override
            public void onResponse(Call<doctors> call, Response<doctors> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DoctorCrudActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(DoctorCrudActivity.this, "Doctor Info Added to Database", Toast.LENGTH_SHORT).show();
                docFname.getText().clear();
                docLname.getText().clear();
                docSpecialist.getText().clear();
                docGender.getText().clear();
                docPrice.getText().clear();
                imgAdminDoctorProfile.setImageResource(0);
                imgAdminDoctorProfile.destroyDrawingCache();
                imgAdminDoctorProfile.setImageResource(R.drawable.noimage);

            }

            @Override
            public void onFailure(Call<doctors> call, Throwable t) {
                Toast.makeText(DoctorCrudActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void OpenAdminDashboard(View view) {
        Intent openadmindashboard = new Intent(this, AdmindashActivity.class);
        startActivity(openadmindashboard);
    }

    public void OpenDocPage(View view) {
        Intent opendocpage = new Intent(this, DoctorActivity.class);
        startActivity(opendocpage);
    }

}
