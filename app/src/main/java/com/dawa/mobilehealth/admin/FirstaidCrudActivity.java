package com.dawa.mobilehealth.admin;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.dawa.api.doctor_api;
import com.dawa.api.firstaid_api;
import com.dawa.mobilehealth.R;
import com.dawa.model.Instructions;
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

public class FirstaidCrudActivity extends AppCompatActivity {

    private CircleImageView imgFirstaidinfo;
    private EditText firstaidcodename, firstaidinstruction, firstaiddescription;
    private Button btnAddFirstaid;
    String imagePath;
    private String imageName = "";
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_firstaid_crud);

        imgFirstaidinfo = findViewById(R.id.imgfirstaidinfo);
        firstaidcodename = findViewById(R.id.crud_firstaid_codename);
        firstaidinstruction = findViewById(R.id.crud_firstaid_treatment);
        firstaiddescription = findViewById(R.id.crud_firstaid_treatmentdetails);
        btnAddFirstaid = findViewById(R.id.btnAddFirstaid);


        imgFirstaidinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
        btnAddFirstaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageOnly();
                addFirstaid();
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
                imgFirstaidinfo.setImageURI(uri);
                imagePath = getRealPathFromUri(uri);
            } else {
                Toast.makeText(FirstaidCrudActivity.this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(FirstaidCrudActivity.this, "image could not upload", Toast.LENGTH_SHORT).show();
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

            firstaid_api firstaidAPI = url.getInstance().create(firstaid_api.class);
            Call<ImageResponse> responseBodyCall = firstaidAPI.FirstaidImgUpload(url.token,body);

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

            Toast.makeText(FirstaidCrudActivity.this, "Please insert picture", Toast.LENGTH_SHORT).show();
//            onStop();

        }
    }

    private void addFirstaid() {

        final String facodename = firstaidcodename.getText().toString();
        if( firstaidcodename.getText().toString().length() == 0 )
            firstaidcodename.setError( "Codename is required!" );
        String fainstruction = firstaidinstruction.getText().toString();
        if( firstaidinstruction.getText().toString().length() == 0 )
            firstaidinstruction.setError( "Instruction is required!" );
        String fadescription = firstaiddescription.getText().toString();
        if( firstaiddescription.getText().toString().length() == 0 )
            firstaiddescription.setError( "Description is required!" );

        Instructions Instructions = new Instructions(facodename, fainstruction, fadescription, imageName);

        firstaid_api firstaidAPI = url.getInstance().create(firstaid_api.class);
        Call<Instructions> addDocCall = firstaidAPI.addFirstaid(Instructions);

        addDocCall.enqueue(new Callback<Instructions>() {
            @Override
            public void onResponse(Call<Instructions> call, Response<Instructions> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(FirstaidCrudActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(FirstaidCrudActivity.this, "Firstaid Info Added to Database", Toast.LENGTH_SHORT).show();
                firstaidcodename.getText().clear();
                firstaidinstruction.getText().clear();
                firstaiddescription.getText().clear();
                imgFirstaidinfo.setImageResource(0);
                imgFirstaidinfo.destroyDrawingCache();
                imgFirstaidinfo.setImageResource(R.drawable.noimage);

            }

            @Override
            public void onFailure(Call<Instructions> call, Throwable t) {
                Toast.makeText(FirstaidCrudActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void OpenAdminDashboard(View view) {
        Intent openadmindashboard = new Intent(this, AdmindashActivity.class);
        startActivity(openadmindashboard);
    }

    public void OpenFirstaidDash(View view) {
        Intent openfirstaiddash = new Intent(this, FirstaidActivity.class);
        startActivity(openfirstaiddash);
    }

}
