package com.dawa.mobilehealth.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.dawa.mobilehealth.R;



public class DoctorActivity extends AppCompatActivity {

    LinearLayout lldoctorinfo, lldocadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_doctor_dashboard);

        lldoctorinfo = findViewById(R.id.llDoctorInfo);
        lldocadd = findViewById(R.id.llDoctorAdd);

        lldoctorinfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent openDoctorInfo = new Intent(DoctorActivity.this, DoctorInfoActivity.class);
                startActivity(openDoctorInfo);
            }
        });

        //Admin Doctor Add update
        lldocadd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent openDocAdd = new Intent(DoctorActivity.this, DoctorCrudActivity.class);
                startActivity(openDocAdd);
            }
        });
    }


    public void OpenAdminDashboard(View view) {
        Intent openadmindashboard = new Intent(this, AdmindashActivity.class);
        startActivity(openadmindashboard);
    }

}