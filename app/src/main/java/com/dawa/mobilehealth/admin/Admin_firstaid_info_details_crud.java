package com.dawa.mobilehealth.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dawa.api.admin_api;
import com.dawa.mobilehealth.R;
import com.dawa.model.Instructions;

import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_firstaid_info_details_crud extends AppCompatActivity {

    TextView codename,instruction,description;
    CircleImageView imgAdminFirstaid;
    ImageView firstaidinfodelete;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_firstaid_info_details_crud);

        codename = findViewById(R.id.firstaid_codename);
        instruction = findViewById(R.id.firstaid_instruction);
        description = findViewById(R.id.firstaid_description);
        imgAdminFirstaid = findViewById(R.id.imgAdminFirstaid);

        firstaidinfodelete = findViewById(R.id.imgdeleteuserinfo);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            codename.setText(bundle.getString("codename"));
            instruction.setText(bundle.getString("instruction"));
            description.setText(bundle.getString("description"));
            String imagepath = url.BASE_URL + bundle.getString("image");
            Picasso.get().load(imagepath).into(imgAdminFirstaid);
        }

        firstaidinfodelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletedocinfo();
            }

            private void deletedocinfo() {
                admin_api firstaidApi = url.getInstance().create(admin_api.class);
                Call<Instructions> voidCall = firstaidApi.deleteFirstaidPost(url.token, id);
                voidCall.enqueue(new Callback<Instructions>() {
                    @Override
                    public void onResponse(Call<Instructions> call, Response<Instructions> response) {
                        if (!response.isSuccessful()) {

                            Toast.makeText(Admin_firstaid_info_details_crud.this, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();

                        }
                        Toast.makeText(Admin_firstaid_info_details_crud.this, "Deleted Firstaid Details Successfully !!!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Instructions> call, Throwable t) {
                        Toast.makeText(Admin_firstaid_info_details_crud.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}