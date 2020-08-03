package com.poll.mobilehealthwearable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    ImageView imgHealthrecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        imgHealthrecord = findViewById(R.id.imgHealthrecord);

        imgHealthrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openqrscan = new Intent(Dashboard.this, UpdateHealthRecordActivity.class);
                startActivity(openqrscan);
            }
        });

    }




    }
