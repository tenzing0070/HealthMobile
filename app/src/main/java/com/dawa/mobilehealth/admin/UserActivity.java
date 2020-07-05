package com.dawa.mobilehealth.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.dawa.mobilehealth.R;

public class UserActivity extends AppCompatActivity {

    LinearLayout lluserinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_dashboard);

        lluserinfo = findViewById(R.id.llUserInfo);

        lluserinfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent openUserInfo = new Intent(UserActivity.this, UserInfoActivity.class);
                startActivity(openUserInfo);
            }
        });
    }


    public void OpenAdminDashboard(View view) {
        Intent openadmindashboard = new Intent(this, AdmindashActivity.class);
        startActivity(openadmindashboard);
    }
}
