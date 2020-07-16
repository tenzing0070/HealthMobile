package com.dawa.mobilehealth.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dawa.adapter.FaqInfoAdapter;
import com.dawa.api.admin_api;
import com.dawa.mobilehealth.R;
import com.dawa.model.Faqs;
import com.dawa.model.doctors;
import com.dawa.url.url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_faq_info_details_crud extends AppCompatActivity {

    TextView question, answer;
    ImageView faqinfodelete;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_faq_info_details_crud);
        question = findViewById(R.id.admin_question);
        answer = findViewById(R.id.admin_answer);
        faqinfodelete = findViewById(R.id.imgFaqInfoDel);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            question.setText(bundle.getString("question"));
            answer.setText(bundle.getString("answer"));

        }

        faqinfodelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletefaqinfo();
            }

            private void deletefaqinfo() {
                admin_api docApi = url.getInstance().create(admin_api.class);
                Call<Faqs> voidCall = docApi.deleteFaqPost(url.token, id);
                voidCall.enqueue(new Callback<Faqs>() {
                    @Override
                    public void onResponse(Call<Faqs> call, Response<Faqs> response) {
                        if (!response.isSuccessful()) {

                            Toast.makeText(Admin_faq_info_details_crud.this, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();

                        }
                        Toast.makeText(Admin_faq_info_details_crud.this, "Deleted Faq Details Successfully !!!", Toast.LENGTH_SHORT).show();
                        Intent openFaqinfo = new Intent(Admin_faq_info_details_crud.this, FaqInfoActivity.class);
                        startActivity(openFaqinfo);
                    }

                    @Override
                    public void onFailure(Call<Faqs> call, Throwable t) {
                        Toast.makeText(Admin_faq_info_details_crud.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
}