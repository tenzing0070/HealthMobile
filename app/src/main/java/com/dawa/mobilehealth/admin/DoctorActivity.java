package com.dawa.mobilehealth.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.dawa.mobilehealth.AdmindashActivity;
import com.dawa.mobilehealth.R;

public class DoctorActivity extends AppCompatActivity {

    LinearLayout lldoctorinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_doctor_dashboard);

        lldoctorinfo = findViewById(R.id.llDoctorInfo);

        lldoctorinfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent openDoctorInfo = new Intent(DoctorActivity.this, DoctorInfoActivity.class);
                startActivity(openDoctorInfo);
            }
        });
    }


    public void OpenAdminDashboard(View view) {
        Intent openadmindashboard = new Intent(this, AdmindashActivity.class);
        startActivity(openadmindashboard);
    }

}