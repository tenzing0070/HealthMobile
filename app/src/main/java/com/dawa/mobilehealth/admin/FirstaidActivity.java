package com.dawa.mobilehealth.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.dawa.mobilehealth.R;

public class FirstaidActivity extends AppCompatActivity {

    LinearLayout llfirstaidinfo, llfirstaidadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_firstaid_dashboard);

        llfirstaidinfo = findViewById(R.id.llFirstaidInfo);
        llfirstaidadd = findViewById(R.id.llFirstaidDash);


// Admin firstaid info display
        llfirstaidinfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent openFirstaidDash = new Intent(FirstaidActivity.this, FirstaidInfoActivity.class);
                startActivity(openFirstaidDash);
            }
        });


    // Admin firstaid info ADD

        llfirstaidadd.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent openFirstaidCRUD = new Intent(FirstaidActivity.this, FirstaidCrudActivity.class);
            startActivity(openFirstaidCRUD);
        }
    });
}


    public void OpenAdminDashboard(View view) {
        Intent openadmindashboard = new Intent(this, AdmindashActivity.class);
        startActivity(openadmindashboard);
    }
}
