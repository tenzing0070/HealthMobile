package com.dawa.mobilehealth.admin;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dawa.api.faq_api;
import com.dawa.mobilehealth.R;
import com.dawa.model.Faqs;

import com.dawa.url.url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaqCrudActivity extends AppCompatActivity {

    private EditText faqQuestion, faqAnswer;
    private Button btnAddFaq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_faq_crud);

        faqQuestion = findViewById(R.id.crud_faq_question);
        faqAnswer = findViewById(R.id.crud_faq_answer);

        btnAddFaq = findViewById(R.id.btnAddFaq);


        btnAddFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addFaq();
            }
        });
    }


    private void addFaq() {

        final String faqquestion = faqQuestion.getText().toString();
        String faqanswer = faqAnswer.getText().toString();

        Faqs Faqs = new Faqs(faqquestion, faqanswer);

        faq_api faqAPI = url.getInstance().create(faq_api.class);
        Call<Faqs> addFaqCall = faqAPI.addFaq(Faqs);

        addFaqCall.enqueue(new Callback<Faqs>() {
            @Override
            public void onResponse(Call<Faqs> call, Response<Faqs> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(FaqCrudActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(FaqCrudActivity.this, "Faq Info Added to Database", Toast.LENGTH_SHORT).show();
                faqQuestion.getText().clear();
                faqAnswer.getText().clear();

            }

            @Override
            public void onFailure(Call<Faqs> call, Throwable t) {
                Toast.makeText(FaqCrudActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void OpenAdminDashboard(View view) {
        Intent openadmindashboard = new Intent(this, AdmindashActivity.class);
        startActivity(openadmindashboard);
    }

    public void OpenFaqDash(View view) {
        Intent opendfaqdash = new Intent(this, FaqActivity.class);
        startActivity(opendfaqdash);
    }


}
