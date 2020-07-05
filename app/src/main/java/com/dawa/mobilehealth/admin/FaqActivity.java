package com.dawa.mobilehealth.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.dawa.mobilehealth.R;

public class FaqActivity extends AppCompatActivity {


    LinearLayout llfaqinfo, llfaqadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_faq_dashboard);

        llfaqinfo = findViewById(R.id.llFaqInfo);
        llfaqadd = findViewById(R.id.llFaqAdd);

        // Admin open Faq details
        llfaqinfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent openFaqInfo = new Intent(FaqActivity.this, FaqInfoActivity.class);
                startActivity(openFaqInfo);
            }
        });

        // Admin open Faq Add info
        llfaqadd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent openFaqAdd = new Intent(FaqActivity.this, FaqCrudActivity.class);
                startActivity(openFaqAdd);
            }
        });

    }
        public void OpenAdminDashboard(View view) {
            Intent openadmindashboard = new Intent(this, AdmindashActivity.class);
            startActivity(openadmindashboard);
        }

}
