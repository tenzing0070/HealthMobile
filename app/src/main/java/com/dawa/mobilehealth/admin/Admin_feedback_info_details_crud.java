package com.dawa.mobilehealth.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dawa.api.admin_api;
import com.dawa.mobilehealth.R;
import com.dawa.model.Faqs;
import com.dawa.model.feedbacks;
import com.dawa.url.url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_feedback_info_details_crud extends AppCompatActivity {

    TextView email, message;
    ImageView feedbackinfodelete;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_feedback_info_details_crud);
        email = findViewById(R.id.user_feedback_email);
        message = findViewById(R.id.user_feedback_message);
        feedbackinfodelete = findViewById(R.id.imgFeedbackInfoDelete);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            email.setText(bundle.getString("email"));
            message.setText(bundle.getString("message"));

        }

        feedbackinfodelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletefeedbackinfo();
            }

            private void deletefeedbackinfo() {
                admin_api feedApi = url.getInstance().create(admin_api.class);
                Call<feedbacks> voidCall = feedApi.deleteFeedbackPost(url.token, id);
                voidCall.enqueue(new Callback<feedbacks>() {
                    @Override
                    public void onResponse(Call<feedbacks> call, Response<feedbacks> response) {
                        if (!response.isSuccessful()) {

                            Toast.makeText(Admin_feedback_info_details_crud.this, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();

                        }
                        Toast.makeText(Admin_feedback_info_details_crud.this, "Deleted Faq Details Successfully !!!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<feedbacks> call, Throwable t) {
                        Toast.makeText(Admin_feedback_info_details_crud.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
}